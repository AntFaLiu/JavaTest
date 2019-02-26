package mySemaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BadLockTest {
    protected ExecutorService executorService = Executors.newCachedThreadPool();
    protected Task1 test1 = new Task1();
    protected Task2 test2 = new Task2();
    protected Semaphore s1 = new Semaphore(1);
    protected Semaphore s2 = new Semaphore(1);

    public static void main(String[] args) {
        BadLockTest test = new BadLockTest();
        for (int i = 0; i < 50; i++) {
            test.test1.setCount(i);
            test.test2.setCount(i);
            test.executorService.execute(test.test1);
            test.executorService.execute(test.test2);
        }
    }

    class Task1 implements Runnable {
        public int count;

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            try {
                if (s2.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println("task1得到obj1对象锁" + count);
                    if (s1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println("task1得到obj2对象锁" + count);
                    }
                }
                s2.release();
                s1.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Task2 implements Runnable {
        public int count;

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            //  synchronized (obj2) {
            try {
                if (s1.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println("task2得到obj1对象锁" + count);
                    if (s2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println("task2得到obj2对象锁" + count);
                    }
                }
                s1.release();
                s2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


//public class BadLockTest {
//    protected Object obj1 = new Object();
//    protected Object obj2 = new Object();
//    protected ExecutorService executorService = Executors.newCachedThreadPool();
//    protected Task1 test1 = new Task1();
//    protected Task2 test2 = new Task2();
//
//    public static void main(String[] args) {
//        BadLockTest test = new BadLockTest();
//        for (int i = 0; i < 50; i++) {
//            test.test1.setCount(i);
//            test.test2.setCount(i);
//            test.executorService.execute(test.test1);
//            test.executorService.execute(test.test2);
//        }
//    }
//
//    class Task1 implements Runnable {
//        public int count;
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        @Override
//        public void run() {
//            synchronized (obj1) {
//                System.out.println("task1得到obj1对象锁" + count);
//                synchronized (obj2) {
//                    System.out.println("task1得到obj2对象锁" + count);
//                }
//            }
//        }
//    }
//
//    class Task2 implements Runnable {
//        public int count;
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        @Override
//        public void run() {
//            synchronized (obj2) {
//                System.out.println("task2得到obj1对象锁" + count);
//                synchronized (obj1) {
//                    System.out.println("task2得到obj2对象锁" + count);
//                }
//            }
//        }
//    }
//}


