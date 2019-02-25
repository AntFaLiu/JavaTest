package producerAndConsumer.conAndPro;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer1 extends Thread {
    private List<Integer> cache;
    private ReentrantLock putLock;
    private ReentrantLock takeLock;
    private Condition notEmpty;
    private Condition notFull;
    private Integer deafultNum = 100;
    private Random random = new Random();
    public Producer1(List<Integer> cache, ReentrantLock putLock,ReentrantLock takeLock,Condition noteEmpty,Condition notFull){
        this.cache = cache;
        this.putLock = putLock;
        this.takeLock = takeLock;
        this.notEmpty = noteEmpty;
        this.notFull = notFull;
    }

    @Override
    public void run() {
        while (deafultNum-- > 0) {
            putLock.lock();
            try {
                while (cache.size() == 6) {
                    notFull.await();
                }
                //thread.sleep(random.nextInt(2000));
                Integer value = random.nextInt(100);
                cache.add(value);
                System.out.println(Thread.currentThread().getName()+" producer:"+value);


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                putLock.unlock();
            }
            takeLock.lock();
            try{
                notEmpty.signal();
            }finally {
                takeLock.unlock();
            }
        }
    }
}