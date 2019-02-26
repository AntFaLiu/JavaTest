package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepInterruptTest {
    public static void main(String[] args) {
        SleepThread t1 = new SleepThread();
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        t1.interrupt();//主动打断线程，使SleepThread线程抛出异常
    }
}

class SleepThread extends Thread {
    public void run() {
        while (true) {
            try {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                System.out.println(sim.format(new Date()));
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("线程中断");
                return;
            }
        }
    }
}