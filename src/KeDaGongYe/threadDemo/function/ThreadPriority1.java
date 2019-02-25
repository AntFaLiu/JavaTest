package keDaGongYe.threadDemo.function;

import java.util.Random;

//验证线程具有规则性
//线程一大部分情况下是先于线程二完成的
public class ThreadPriority1 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            MyThread1 myThread1 = new MyThread1();
            myThread1.setPriority(1);
            myThread1.start();
            MyThread2 myThread2 = new MyThread2();
            myThread2.setPriority(10);
            myThread2.start();
        }
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 50000; j++) {
                Random random = new Random();
                random.nextInt();
                sum = sum + i;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("-------------thread 1 use time = " + (end - start));
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 50000; j++) {
                Random random = new Random();
                random.nextInt();
                sum = sum + i;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("*****************thread 2 use time = " + (end - start));
    }
}
