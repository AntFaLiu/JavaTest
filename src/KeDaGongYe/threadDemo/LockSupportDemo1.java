package KeDaGongYe.threadDemo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park(obj);
                System.out.println("thread...");

            }
        });
        thread.start();

        Thread.sleep(1000);
        System.out.println("main.....");
        LockSupport.unpark(thread);

        Thread.sleep(1000);
    }
}