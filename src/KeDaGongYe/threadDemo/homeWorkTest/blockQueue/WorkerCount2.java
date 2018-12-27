package KeDaGongYe.threadDemo.homeWorkTest.blockQueue;

import java.util.concurrent.CountDownLatch;

public class WorkerCount2 extends Thread {
    private String name;
    private long time;
    private CountDownLatch countDownLatch;

    public WorkerCount2(String name, long time, CountDownLatch countDownLatch) {
        this.name = name;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + "开始阶段1工作");
            Thread.sleep(time);
            System.out.println(name + "阶段1完成, 耗时："+ time);
            countDownLatch.countDown();


            System.out.println(name + "开始阶段2工作");
            Thread.sleep(time);
            System.out.println(name + "阶段2完成, 耗时："+ time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int COUNT = 2;
        final CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        WorkerCount2 worker0 = new WorkerCount2("lilei-0", (long)(Math.random() * 10000), countDownLatch);
        WorkerCount2 worker1 = new WorkerCount2("lilei-1", (long)(Math.random() * 10000), countDownLatch);
        worker0.start();
        worker1.start();
        countDownLatch.await();
        System.out.println("准备工作就绪");

//        WorkerCount2 worker2 = new WorkerCount2("lilei-2", (long)(Math.random() * 10000), countDownLatch);
//        worker2.start();
//        Thread.sleep(10000);
    }
}
