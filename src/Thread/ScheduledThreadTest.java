package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        System.out.println("main开始时间:" + MyRunnable.now());
        for (int i = 0; i < 3; i++) {
            MyRunnable myRunnable = new MyRunnable("thread-" + i);
            System.out.println(myRunnable.getName() + "开始时间:" + MyRunnable.now());
            pool.schedule(myRunnable, 5, TimeUnit.SECONDS);//延时5秒执行
            //在一次调用完成和下一次调用开始之间有长度为delay的延迟
            //pool.scheduleWithFixedDelay(myRunnable,5,5,TimeUnit.SECONDS);
        }
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();//7秒后不再接受执行线程
        while (!pool.isTerminated()) {
            //等待所有线程结束
        }
        System.out.println("main结束时间:" + MyRunnable.now());
    }
}

class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    static String now() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(getName() + "true start:" + now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + "true end:" + now());
    }
}