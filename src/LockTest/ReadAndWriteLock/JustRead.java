package lockTest.ReadAndWriteLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JustRead {
    CountDownLatch latch = new CountDownLatch(2);
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//    MyReadAndWriteLock myReadAndWriteLock = new MyReadAndWriteLock();
//    ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        final JustRead justRead = new JustRead();
        long start = System.currentTimeMillis();
        System.out.println("  start time:" + start);
        new Thread(new Runnable() {
            @Override
            public void run() {
                justRead.get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                justRead.get(Thread.currentThread());
            }
        }).start();
        try {
            justRead.latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("end time:" + end);
        System.out.println("消耗：" + (end - start) + "ms");
    }

    public void get(Thread thread)  {

        try {
            lock.readLock().lock();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
            latch.countDown();
        } finally {
            lock.readLock().unlock();
        }
    }
}
