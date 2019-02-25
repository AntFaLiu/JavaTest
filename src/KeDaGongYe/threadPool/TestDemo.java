package keDaGongYe.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1, new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = Executors.defaultThreadFactory().newThread(r);
                        thread.setDaemon(true);
                        return thread;
            }
        });

        pool.execute(() -> System.out.println(10));

        System.out.println(pool.isShutdown());
    }
}
