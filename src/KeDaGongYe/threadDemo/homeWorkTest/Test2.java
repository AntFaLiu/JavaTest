package keDaGongYe.threadDemo.homeWorkTest;

/**
 * 用Java写一个会导致死锁的程序
 */
public class Test2 {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();
        A a = new A(lockA, lockB);
        B b = new B(lockA, lockB);
        a.start();
        b.start();
    }

    static class A extends Thread{
        private final Object lockA;
        private final Object lockB;
        A(Object lockA, Object lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override public void run() {
            synchronized (lockA){
                try {
                    Thread.sleep(1000);
                    synchronized (lockB){
                        System.out.println("Hello A");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class B extends Thread{
        private final Object lockA;
        private final Object lockB;
        B(Object lockA, Object lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override public void run() {
            synchronized (lockB){
                try {
                    Thread.sleep(1000);
                    synchronized (lockA){
                        System.out.println("Hello B");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
