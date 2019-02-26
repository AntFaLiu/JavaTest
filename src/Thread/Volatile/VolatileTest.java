package thread.Volatile;

/**
 * 说明其可以保证每次都从主内存中拿数据
 */
public class VolatileTest {
    public static volatile Boolean stop = false;

    public static void main(String args[]) throws InterruptedException {
        Thread testThread = new Thread() {
            @Override
            public void run() {
                int i = 1;
                while (!stop) {
                    i++;
                }
                System.out.println("thread stop i=" + i);
            }
        };
        testThread.start();
        Thread.sleep(100);
        stop = true;
        System.out.println("now, in main thread stop is: " + stop);
//        testThread.join();
    }
}
