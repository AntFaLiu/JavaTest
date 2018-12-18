package KeDaGongYe.threadDemo.homeWorkTest.blockQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenBlocingQueue<E> {
    private final List list;
    private final int limit;//有大小限制的
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public ReenBlocingQueue(int limit) {
        this.limit = limit;
        this.list = new LinkedList<E>();
    }

    public static void main(String[] args) {
        final ReenBlocingQueue<Integer> reenBlocingQueue = new ReenBlocingQueue(10);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exec.execute(new ReenBlocingQueue.TestRunnable(reenBlocingQueue));
        }
        exec.shutdown();
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == limit) {
                notFull.await();
            }
            list.add(e);
            System.out.println("put : " + e);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == 0) {
                notEmpty.await();
            }
            E remove = (E) list.remove(0);
            System.out.println("take : " + remove);
            notFull.signalAll();
            return remove;
        } finally {
            lock.unlock();
        }
    }

    static class TestRunnable implements Runnable {
        private final ReenBlocingQueue<Integer> reenBlocingQueue;

        TestRunnable(ReenBlocingQueue<Integer> myBlocingQueue) {
            this.reenBlocingQueue = myBlocingQueue;
        }

        @Override
        public void run() {
            Random random = new Random();
            int r = random.nextInt(100);
            //生成随机数,按照一定比率读取或者放入，可以更改！！！
            try {
                if (r < 30) {

                    reenBlocingQueue.put(r);

                } else {
                    reenBlocingQueue.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
