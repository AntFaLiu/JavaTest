package keDaGongYe.threadDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoubleLock {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        DoubleLock test = new DoubleLock();
        test.method1();
    }

    public synchronized void method1() {
        method2();
    }

    public synchronized void method2() {
        System.out.println("*******Hello method2******");
    }
}
