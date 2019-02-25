package keDaGongYe.threadDemo.homeWorkTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * 现有的程序代码模拟产生了16个日志对象，并且需要运行16秒才能打印完这些日志，请在程序中
 * 增加4个线程去调用parseLog() 方法来分头打印这16个日志对象，程序只需要运行4秒即可打印完这些日志对象。
 */
//public class Test6 {
//    public static void main(String[] args) {
//
//        long begin = System.currentTimeMillis() / 1000;
//        System.out.println("begin:" + begin);
//            /*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
//            修改程序代码，开四个线程让这16个对象在4秒钟打完。
//            */
//        for (int i = 0; i < 16; i++) {
//            final String log = "" + (i + 1);
//            {
//                Test6.parseLog(log);
//            }
//        }
//        long end = System.currentTimeMillis() / 1000;
//        System.out.println("耗时:" + (end - begin) + "s");
//    }
//
//    //parseLog方法内部的代码不能改动
//    public static void parseLog(String log) {
//        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
//        try {
//            thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}

public class Test6 {

    public static void main(String[] args) {
        //定义一个线程共享的队列容器，可以使得数据由队列的一端输入，从另外一端输出
        CountDownLatch lock = new CountDownLatch(16);
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
        long begin = System.currentTimeMillis() / 1000;
        System.out.println("begin:" + begin);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            parseLog(queue.take());
                            lock.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }).start();
        }


        /*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
        修改程序代码，开四个线程让这16个对象在4秒钟打完。
        */
        for (int i = 0; i < 16; i++) {
            final String log = "" + (i + 1);
            {
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            lock.await();
            long end = System.currentTimeMillis() / 1000;
            System.out.println("耗时:" + (end - begin) + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //parseLog方法内部的代码不能改动
    public static void parseLog(String log) {
        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
