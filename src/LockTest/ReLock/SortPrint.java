package lockTest.ReLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortPrint {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                }
                lock.unlock();
            }
        }, "ThreadA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                }
                lock.unlock();
            }
        }, "ThreadB").start();

    }
}
