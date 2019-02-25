package producerAndConsumer.conAndPro;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer1 extends Thread {
    private List<Integer> cache;
    private ReentrantLock putLock;
    private ReentrantLock takeLock;
    private Condition notEmpty;
    private Condition notFull;
    private Integer deafultNum = 100;
    private Random random = new Random();

    public Consumer1(List<Integer> cache, ReentrantLock putLock, ReentrantLock takeLock, Condition noteEmpty, Condition notFull) {
        this.cache = cache;
        this.putLock = putLock;
        this.takeLock = takeLock;
        this.notEmpty = noteEmpty;
        this.notFull = notFull;
    }

    @Override
    public void run() {
        while (deafultNum-- > 0) {
            takeLock.lock();
            try {
                while (cache.size() == 0) {
                    notEmpty.await();
                }
                //thread.sleep(random.nextInt(2000));
                Integer value = cache.remove(0);
                System.out.println(Thread.currentThread().getName() + " consumer:" + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                takeLock.unlock();
            }
            putLock.lock();
            try{
                notFull.signal();
            }finally {
                putLock.unlock();
            }

        }
    }
}

