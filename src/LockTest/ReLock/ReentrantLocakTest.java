package LockTest.ReLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocakTest implements Runnable {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    @Override
    public void run() {
        testMethod();
    }

    public void testMethod() {
        lock.lock();
        try {
            System.out.println("开始wait");
            //condition.await();
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + (" " + (i + 1)));
            }
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

//    public void testMethod() {
//        lock.lock();
//        for (int i = 0; i < 5; i++) {
//            System.out.println("ThreadName=" + Thread.currentThread().getName()
//                    + (" " + (i + 1)));
//        }
//        lock.unlock();
//    }

    public static void main(String[] args) {
        ReentrantLocakTest test = new ReentrantLocakTest();
        //ReentrantLocakTest test1 = new ReentrantLocakTest();
        Thread t1 = new Thread(test,"A");
        Thread t2 = new Thread(test,"B");
        t1.start();
        t2.start();
    }
}
