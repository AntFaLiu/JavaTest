package keDaGongYe.threadDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {
    private final static int THREAD_NUM = 10;
    private CountDownLatch lock1 = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) {
        CountDownLatch lock = new CountDownLatch(THREAD_NUM);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            exec.submit(new CountdownLatchTask(lock, "thread-" + i));
        }
        exec.shutdown();
    }

    static class CountdownLatchTask implements Runnable {
        private final CountDownLatch lock;
        private final String threadName;

        CountdownLatchTask(CountDownLatch lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName + " ******* 阶段一 完成 ********");


            try {
                lock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName + "********* 阶段二 执行完成 ****** ");
        }
    }

}
