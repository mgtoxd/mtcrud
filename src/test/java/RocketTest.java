import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageBuilder;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.apis.producer.Transaction;
import org.apache.rocketmq.client.apis.producer.TransactionResolution;
import org.apache.rocketmq.client.java.message.MessageBuilderImpl;
import org.apache.rocketmq.shaded.com.google.common.base.Strings;
import pers.mtx.grpc.mtcrud.PostParams;

public class RocketTest {
    //演示demo，模拟订单表查询服务，用来确认订单事务是否提交成功。
    private static boolean checkOrderById(String orderId) {
        return true;
    }
    //演示demo，模拟本地事务的执行结果。
    private static boolean doLocalTransaction() {
        return true;
    }
    public static void main(String[] args) throws ClientException {
        //接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081;xxx:8081。
        String endpoint = "localhost:8081";
        //消息发送的目标Topic名称，需要提前创建。
        String topic = "TestRCTTopic";
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        MessageBuilder messageBuilder = new MessageBuilderImpl();
        //构造事务生产者：事务消息需要生产者构建一个事务检查器，用于检查确认异常半事务的中间状态。
        Producer producer = provider.newProducerBuilder()
                .setTransactionChecker(messageView -> {
                    /**
                     * 事务检查器一般是根据业务的ID去检查本地事务是否正确提交还是回滚，此处以订单ID属性为例。
                     * 在订单表找到了这个订单，说明本地事务插入订单的操作已经正确提交；如果订单表没有订单，说明本地事务已经回滚。
                     */
                    final String orderId = messageView.getProperties().get("OrderId");
                    System.out.println(messageView);
                    if (Strings.isNullOrEmpty(orderId)) {
                        // 错误的消息，直接返回Rollback。
                        return TransactionResolution.ROLLBACK;
                    }
                    return checkOrderById(orderId) ? TransactionResolution.COMMIT : TransactionResolution.ROLLBACK;
                }).setTopics(topic).setClientConfiguration(configuration)
                .build();
        //开启事务分支。
        final Transaction transaction;
        try {
            transaction = producer.beginTransaction();
        } catch (ClientException e) {
            e.printStackTrace();
            //事务分支开启失败，直接退出。
            return;
        }
        PostParams build = PostParams.newBuilder().setId("2").setDbName("0").setTbName("1").putValueMap("3","7777").build();

        Message message = messageBuilder.setTopic(topic)
                //设置消息索引键，可根据关键字精确查找某条消息。
                .setKeys("POST")
                //设置消息Tag，用于消费端根据指定Tag过滤消息。
                .setTag("messageTag")
                //一般事务消息都会设置一个本地事务关联的唯一ID，用来做本地事务回查的校验。
                //消息体。
                .setBody(build.toByteArray())
                .build();
        //发送半事务消息
        final SendReceipt sendReceipt;
        try {
            sendReceipt = producer.send(message, transaction);
        } catch (ClientException e) {
            //半事务消息发送失败，事务可以直接退出并回滚。
            return;
        }
        /**
         * 执行本地事务，并确定本地事务结果。
         * 1. 如果本地事务提交成功，则提交消息事务。
         * 2. 如果本地事务提交失败，则回滚消息事务。
         * 3. 如果本地事务未知异常，则不处理，等待事务消息回查。
         *
         */
        boolean localTransactionOk = doLocalTransaction();
        if (localTransactionOk) {
            try {
                transaction.commit();
                System.out.println("提交成功");
            } catch (ClientException e) {
                // 业务可以自身对实时性的要求选择是否重试，如果放弃重试，可以依赖事务消息回查机制进行事务状态的提交。
                e.printStackTrace();
            }
        } else {
            try {
                transaction.rollback();
            } catch (ClientException e) {
                // 建议记录异常信息，回滚异常时可以无需重试，依赖事务消息回查机制进行事务状态的提交。
                e.printStackTrace();
            }
        }
    }
}