package KeDaGongYe.threadDemo;



import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadWayDemo {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },"2");
        Thread t = new Thread(futureTask);
        t.start();
        try {
            Thread.sleep(1000);
            futureTask.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
