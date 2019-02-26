package keDaGongYe.threadDemo;



import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadWayDemo {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo);
        Thread t = new Thread(futureTask);
        t.start();

        try {
            Thread.sleep(1000);
            futureTask.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futureTask.cancel(false);

    }
}

class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        while (true){
            System.out.println("call");
            Thread.sleep(1000);
        }
    }
}
