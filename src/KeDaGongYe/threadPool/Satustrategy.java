package keDaGongYe.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Satustrategy {
//        ExecutorService exec = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.SECONDS,
//            new LinkedBlockingDeque<Runnable>(5), new ThreadPoolExecutor.AbortPolicy());
//    这里一共有8个任务会被执行
//    ExecutorService exec = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.SECONDS,
//            new LinkedBlockingDeque<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());
//    ExecutorService exec = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.SECONDS,
//            new LinkedBlockingDeque<Runnable>(5), new ThreadPoolExecutor.DiscardPolicy());
    ExecutorService exec = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(5), new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) {
        new Satustrategy().putrunnable();
    }

    private void putrunnable() {
        for (int i = 0; i < 10; i++) {
            exec.submit(new Task());
        }
        exec.shutdown();
    }

    static class Task implements Runnable {
        private static int count = 0;
        private int id = 0;

        public Task() {
            id = ++count;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务" + id + ":" + Thread.currentThread().getName());
        }
    }
}
