package LockTest.ReadAndWriteLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLockTest {
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReadAndWriteLockTest lockTest = new ReadAndWriteLockTest();
        // 建N个线程，同时读
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                lockTest.readFile(Thread.currentThread());
            }
        });
        // 建N个线程，同时写
        ExecutorService service1 = Executors.newCachedThreadPool();
        service1.execute(new Runnable() {
            @Override
            public void run() {
                lockTest.writeFile(Thread.currentThread());
            }
        });
        service.shutdown();
        service1.shutdown();
    }

    // 读操作
    public void readFile(Thread thread) {
        readWriteLock.readLock().lock();
        boolean readLock = readWriteLock.isWriteLocked();
        if (!readLock) {
            System.out.println("当前为读锁！");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
        } finally {
            System.out.println("释放读锁！");
            readWriteLock.readLock().unlock();
        }
    }

    // 写操作
    public void writeFile(Thread thread) {
        readWriteLock.writeLock().lock();
        boolean writeLock = readWriteLock.isWriteLocked();
        if (writeLock) {
            System.out.println("当前为写锁！");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行写操作……");
            }
            System.out.println(thread.getName() + ":写操作完毕！");
        } finally {
            System.out.println("释放写锁！");
            readWriteLock.writeLock().unlock();
        }
    }
}
