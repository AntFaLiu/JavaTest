package producerAndConsumer.conAndPro;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConProMain {
    public static void main(String[] args) {
        //1、一个生产者、一个消费者，仓库大小为1，实现生产者、消费者模型（要求：使用ReentrantLock锁及其Condition机制）
//        ReentrantLock reentrantLock = new ReentrantLock();
//        Condition notFull = reentrantLock.newCondition();
//        Condition notEmpty = reentrantLock.newCondition();
        ArrayList<Integer> list = new ArrayList<>();

//        Producer1 producer1 = new Producer1(list, reentrantLock, notEmpty, notFull);
//        producer1.setName("p1");
//        Consumer1 consumer1 = new Consumer1(list, reentrantLock, notEmpty, notFull);
//        consumer1.setName("c1");
//        producer1.start();
//        consumer1.start();

        //2、一个生产者、一个消费者，仓库大小为6，实现生产者、消费者模型（要求：使用BlockingQueue阻塞队列）
//        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(6);
//        Producer2 producer2 = new Producer2(blockingQueue);
//        producer2.setName("p2");
//        Consumer2 consumer2 = new Consumer2(blockingQueue);
//        consumer2.setName("c2");
//        producer2.start();
//        consumer2.start();
        ReentrantLock putLock = new ReentrantLock();
        Condition notFull = putLock.newCondition();
        ReentrantLock takeLock = new ReentrantLock();
        Condition notEmpty = takeLock.newCondition();
//        //3、三个生产者、三个消费者，仓库大小为6，实现生产者、消费者模型（要求：生产者、消费者可以并发操作）
        for (int i = 0; i < 3; i++) {
            Producer1 pro = new Producer1(list, putLock, takeLock, notEmpty, notFull);
            pro.setName(i + "Producer");
            pro.start();
        }
        for (int i = 0; i < 3; i++) {
            Consumer1 con = new Consumer1(list, putLock, takeLock, notEmpty, notFull);
            con.setName(i + "Consumer");
            con.start();
        }

    }
}
