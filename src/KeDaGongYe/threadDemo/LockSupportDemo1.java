package keDaGongYe.threadDemo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //LockSupport.unpark(thread.currentThread()); //permit++
                LockSupport.park(obj); //permit 0   -1
                System.out.println("thread...");
            }
        });

        Thread.sleep(1000);
        thread.start();
        Thread.sleep(1000);
        System.out.println("main.....");
        LockSupport.unpark(thread);
        System.out.println(LockSupport.getBlocker(thread));
    }
}