package LockTest.SynLockTest;

public class SynchronizedDemo {
    private Object object = new Object();
    public void method() {
        synchronized (SynchronizedDemo.class) {
            System.out.println("Method 1 start");
        }
    }
}
