package KeDaGongYe.threadDemo;



import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadWayDemo {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"2");
    }
}

class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
