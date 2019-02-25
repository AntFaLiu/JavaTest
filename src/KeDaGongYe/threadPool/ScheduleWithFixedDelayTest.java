package keDaGongYe.threadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        System.out.println(new Date().getSeconds());
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "***" + new Date().getSeconds() + "===== I love LSR");
            }
        }, 3, TimeUnit.SECONDS);

//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            //        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(thread.currentThread().getName() + "***" + new Date().getSeconds() + "===== I love LSR");
//            }
//        }, 1, 3, TimeUnit.SECONDS);

    }
}

class ScheduleWithFixedDelayDemo {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        System.out.println(new Date().getSeconds());
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            //        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "***" + new Date().getSeconds() + "===== I love LSR");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 1, 5, TimeUnit.SECONDS);
    }
}