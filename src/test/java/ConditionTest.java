import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 简单示例
public class ConditionTest {

    // 实例化一个ReentrantLock对象
    private final ReentrantLock lock = new ReentrantLock();
    // 为线程A注册一个Condition
    public final Condition conditionA = lock.newCondition();
    // 为线程B注册一个Condition
    public final Condition conditionB = lock.newCondition();

    public void awaitA() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "进入了awaitA方法");
            long timeBefore = System.currentTimeMillis();
            // 执行conditionA等待
//            conditionA.await();
            Thread.sleep(2000);
            long timeAfter = System.currentTimeMillis();

            System.out.println(Thread.currentThread().getName() + "被唤醒");
            System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore) / 1000 + "s");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitC() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "进入了awaitC方法");
            long timeBefore = System.currentTimeMillis();
            // 执行conditionA等待
//            conditionA.await();
            long timeAfter = System.currentTimeMillis();

            System.out.println(Thread.currentThread().getName() + "被唤醒");
            System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore) / 1000 + "s");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            System.out.println(Thread.currentThread().getName() + "进入了awaitB方法");
            long timeBefore = System.currentTimeMillis();
            // 执行conditionA等待
            conditionB.await();
            long timeAfter = System.currentTimeMillis();

            System.out.println(Thread.currentThread().getName() + "被唤醒");
            System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore) / 1000 + "s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signallA() {
        lock.lock();
        try {
            System.out.println("启动唤醒程序");
            // 唤醒所有注册conditionA的线程
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signallB() {
        lock.lock();
        try {
            System.out.println("启动唤醒程序");
            // 唤醒所有注册conditionB的线程
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest conditionService = new ConditionTest();
        new Thread(conditionService::awaitA, "A").start();
        new Thread(conditionService::awaitC, "B").start();

        Thread.sleep(2000);
        // 唤醒持有ConditionA的线程
        conditionService.signallA();

    }


}

