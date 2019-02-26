package keDaGongYe.threadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadWayTest {
    public static void main(String[] args) {
        MyThreadTest threadTest = new MyThreadTest();
        threadTest.start();
        Thread myRunable = new Thread(new MyRunable(),"");
        myRunable.getName();
        myRunable.start();
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> task = new FutureTask<>(callable);
        new Thread(task).start();
        try {
            int i = task.get();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });
        t.start();

    }
}

class MyThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println("MyThreadTest");
    }
}

class MyRunable implements Runnable {

    @Override
    public void run() {
        System.out.println("MyRunable");
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable");
        return 100;
    }
}