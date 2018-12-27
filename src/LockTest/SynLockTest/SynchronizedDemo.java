package LockTest.SynLockTest;

public class SynchronizedDemo {
    private Object object = new Object();//全局变量

    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}
