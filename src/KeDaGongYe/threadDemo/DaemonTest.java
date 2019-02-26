package keDaGongYe.threadDemo;

import java.util.concurrent.TimeUnit;

public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread");
            }
        });
        Thread childThread = new Thread(new ClildThread());
        childThread.setDaemon(true);
        childThread.start();
        thread.start();
        new Thread(new Runnable1()).start();

    }
}

class ClildThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runnable1 implements Runnable {

    @Override
    public void run() {
        System.out.println("**********Runnable1************");
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
