package KeDaGongYe.threadDemo.homeWorkTest;

import java.util.concurrent.CountDownLatch;

public class WorkerCount extends Thread {
    private String name;
    private long time;
    private CountDownLatch countDownLatch;

    public WorkerCount(String name, long time, CountDownLatch countDownLatch) {
        this.name = name;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + "开始工作");
            Thread.sleep(time);
            System.out.println(name + "工作完成, 耗时：" + time);
            countDownLatch.countDown();
            System.out.println("countDownLatch.getCount():" + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int COUNT = 2;
        final CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        WorkerCount worker0 = new WorkerCount("lilei-0", (long)(Math.random() * 10000), countDownLatch);
        WorkerCount worker1 = new WorkerCount("lilei-1", (long)(Math.random() * 10000), countDownLatch);
        WorkerCount worker2 = new WorkerCount("lilei-2", (long)(Math.random() * 10000), countDownLatch);
        worker0.start();
        worker1.start();
        countDownLatch.await();
        System.out.println("准备工作就绪");
        worker2.start();
        Thread.sleep(10000);
    }
}
