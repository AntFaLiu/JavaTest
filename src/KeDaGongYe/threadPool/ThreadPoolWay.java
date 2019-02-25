package keDaGongYe.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolWay   {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executorSingle = Executors.newSingleThreadExecutor();
        ExecutorService executorFixed = Executors.newFixedThreadPool(2);
        ExecutorService executorScheduled = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        executor.execute(new DemoRunnable());


//        test t = new test(executor);
//        thread thread = new thread(t);
//        //thread.setDaemon(true);
//        thread.start();

//        Future<Integer> future = executorSingle.submit(new MyCallable(7));
//
//
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        executor.shutdownNow();


    }
}

class DemoRunnable implements Runnable {
    @Override
    public void run() {
        //  TODO Auto-generated method stub
        try {
            for (int i = 0;i < 5;i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop ");
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<Integer> {

    int count;

    public MyCallable(int count) {
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {
        if (this.count != 0) {
            return 1000 % count;
        }
        return 0;
    }
}

class test implements Runnable{
    ExecutorService executor;
    test(ExecutorService executor){
        this.executor = executor;
    }
    @Override
    public void run() {
        executor.shutdown();
    }
}