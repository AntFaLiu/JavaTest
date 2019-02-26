package lockTest.SynLockTest;

public class SynchronizedDemo {
    private Object object = new Object();

    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}
