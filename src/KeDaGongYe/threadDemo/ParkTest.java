package keDaGongYe.threadDemo;

import java.util.concurrent.locks.LockSupport;

public class ParkTest {
    public static void main(String[] args) {
        Object obj = new Object();
        LockSupport.park(obj);
        System.out.println("main");
    }
}
