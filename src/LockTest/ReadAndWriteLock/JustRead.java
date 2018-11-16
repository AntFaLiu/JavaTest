package LockTest.ReadAndWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JustRead {
   // ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    MyReadAndWriteLock lock = new MyReadAndWriteLock();
    public static void main(String[] args) {
        final JustRead justRead = new JustRead();
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
    }

    public void get(Thread thread) {
        //lock.readLock().lock();
        try {
            lock.lockRead();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(thread.getName() + "  start time:" + System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
            System.out.println(thread.getName() + "  end time:" + System.currentTimeMillis());
        } finally {
            //lock.readLock().unlock();
            lock.unLockRead();
        }
    }
}
