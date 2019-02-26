package lockTest.ReLock;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args) {
        final TryLockTest test = new TryLockTest();
        new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        Thread t1 = new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        };
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        t1.interrupt();
    }

    public void insert(Thread thread) {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(thread.getName() + "得到了锁");
                    for (int i = 0; i < 5; i++) {
                        arrayList.add(i);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            } else {
                System.out.println(thread.getName() + "获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
