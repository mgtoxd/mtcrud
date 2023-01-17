package pers.mtx.service;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;
import pers.mtx.grpc.mtcrud.DelParams;
import pers.mtx.grpc.mtcrud.PostParams;
import pers.mtx.grpc.mtcrud.PutParams;
import pers.mtx.mt.Crud;
import pers.mtx.mt.CrudFactory;
import pers.mtx.util.LogUtil;
import pers.mtx.util.YmlUtil;

import java.util.Collections;

public class RocketMqServ {
    public static void initRocketMqServ() throws ClientException {
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        //接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081;xxx:8081。
        String endpoints = YmlUtil.getSetting().getRocketmq().getMqEndPoints();
        //为消费者指定所属的消费者分组，Group需要提前创建。
        String consumerGroup = YmlUtil.getSetting().getRocketmq().getConsumerGroup();
        //指定需要订阅哪个目标Topic，Topic需要提前创建。
        String topic = YmlUtil.getSetting().getRocketmq().getTopic();
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(endpoints)
                .build();
        //订阅消息的过滤规则，表示订阅所有Tag的消息。
        String tag = YmlUtil.getSetting().getRocketmq().getTag();
        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);

        //初始化PushConsumer，需要绑定消费者分组ConsumerGroup、通信参数以及订阅关系。
        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
                .setClientConfiguration(clientConfiguration)
                //设置消费者分组。
                .setConsumerGroup(consumerGroup)
                //设置预绑定的订阅关系。
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                //设置消费监听器。
                .setMessageListener(messageView -> {
                    //处理消息并返回消费结果。
                    String key = String.valueOf(messageView.getKeys().toArray()[0]);
                    Crud crud = CrudFactory.getCrudByName(key);
                    try {
                        switch (key){
                            case "PUT":
                                crud.make(PutParams.parseFrom(messageView.getBody()));
                            case "POST":
                                crud.make(PostParams.parseFrom(messageView.getBody()));
                            case "DELETE":
                                crud.make(DelParams.parseFrom(messageView.getBody()));
                        }
                    }catch (Exception e){
                        LogUtil.getExceptionInfo(e);
                    }
                    return ConsumeResult.SUCCESS;
                })
                .build();
    }
}
