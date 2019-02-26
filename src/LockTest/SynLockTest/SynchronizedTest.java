package lockTest.SynLockTest;
//
////首先演示这个方法没有使用锁的时候是什么效果
//public class SynchronizedTest {
//    public static void main(String[] args) {
//        final SynchronizedTest test = new SynchronizedTest();
//
//        new thread(new Runnable() {
//            @Override
//            public void run() {
//                test.method1();
//            }
//        }).start();
//
//        new thread(new Runnable() {
//            @Override
//            public void run() {
//                test.method2();
//            }
//        }).start();
//    }
//
//    public void method1() {
//        System.out.println("Method 1 start");
//        try {
//            System.out.println("Method 1 execute");
//            thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Method 1 end");
//    }
//
//    public void method2() {
//        System.out.println("Method 2 start");
//        try {
//            System.out.println("Method 2 execute");
//            thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Method 2 end");
//    }
//}

public class SynchronizedTest {
    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();
    }

    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }
}