package Thread.Volatile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class volatileTest2 implements Runnable {
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    private List l = new ArrayList();

    public static void main(String[] args) {
        volatileTest2 volatileTest2 = new volatileTest2();
        for (int i = 0; i < 10; i++) {
            volatileTest2.l.add(i);
        }

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(volatileTest2);
            t.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void get() {
        l.remove(0);
    }

    @Override
    public void run() {
        System.out.println(l.size());
        get();
        countDownLatch.countDown();
    }
}
