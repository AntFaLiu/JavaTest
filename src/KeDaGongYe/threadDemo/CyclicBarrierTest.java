package keDaGongYe.threadDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    private final static int THREAD_NUM = 10;

    public static void main(String[] args) {
        CyclicBarrier lock = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("大家都准备完成了");
            }
        });
        //ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new CountdownLatchTask(lock, "thread-" + i)).start();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (i == 4) {
//                lock.reset();
//                System.out.println("**********reset() lock.getNumberWaiting()************ " + lock.getNumberWaiting());
//            }
        }

        //exec.shutdown();
    }

    static class CountdownLatchTask implements Runnable {
        private final CyclicBarrier lock;
        private final String threadName;

        CountdownLatchTask(CyclicBarrier lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }

        @Override
        public void run() {

            System.out.println(threadName + " 准备完成");
            try {
//                System.out.println("lock.getNumberWaiting(): " + lock.getNumberWaiting());
//                System.out.println("lock.getParties():   " + lock.getParties());
                lock.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " 执行完成");
        }
    }
}
