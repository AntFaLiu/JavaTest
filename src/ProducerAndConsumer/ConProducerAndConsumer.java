package producerAndConsumer;


//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @author zhangliang
// * <p>
// * 2016年4月8日 下午5:48:54
// */
//public class ConProducerAndConsumer {
//
//    final Lock lock = new ReentrantLock();
//    final Condition condition = lock.newCondition();
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        ConProducerAndConsumer test = new ConProducerAndConsumer();
//        Producer producer = test.new Producer();
//        Consumer consumer = test.new Consumer();
//
//
//        consumer.start();
//        producer.start();
//    }
//
//    class Consumer extends thread {
//
//        @Override
//        public void run() {
//            consume();
//        }
//
//        private void consume() {
//
//            try {
//                lock.lock();
//                System.out.println("我在等一个新信号" + this.currentThread().getName());
//                condition.await();
//
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } finally {
//                System.out.println("拿到一个信号" + this.currentThread().getName());
//                lock.unlock();
//            }
//
//        }
//    }
//
//    class Producer extends thread {
//
//        @Override
//        public void run() {
//            produce();
//        }
//
//        private void produce() {
//            try {
//                lock.lock();
//                System.out.println("我拿到锁" + this.currentThread().getName());
//                condition.signalAll();
//                System.out.println("我发出了一个信号：" + this.currentThread().getName());
//            } finally {
//                lock.unlock();
//            }
//        }
//    }
//}


import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConProducerAndConsumer {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(10);
    private Lock lock = new ReentrantLock();
    private Condition putCon = lock.newCondition();
    private Condition removeCon = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        ConProducerAndConsumer test = new ConProducerAndConsumer();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        volatile boolean flag = true;

        private void consume() {
            while (flag) {
                lock.lock();
                try {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("队列空，等待数据");
                            removeCon.await();
                        } catch (Exception e) {
                            flag = false;
                        }
                    }
                    queue.poll();                //每次移走队首元素
                    putCon.signalAll();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        volatile boolean flag = true;

        private void produce() {
            while (flag) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满，等待有空余空间");
                            putCon.await();
                        } catch (Exception e) {
                            flag = false;
                        }
                    }
                    queue.offer(1);        //每次插入一个元素
                    removeCon.signalAll();
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}