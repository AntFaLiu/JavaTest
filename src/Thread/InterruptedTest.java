package Thread;

class Test implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                System.out.println("i=" + (i + 1));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class InterruptedTest {
    public static void main(String[] args) {
        Test a = new Test();
        Thread t1 = new Thread(a);
        t1.start();
        t1.interrupt();
        System.out.println("执行睡眠之前1：" + t1.isInterrupted());
        try {
            System.out.println("执行睡眠之前2：" + t1.isInterrupted());
            t1.sleep(1000);//线程进入阻塞状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行睡眠之后：" + t1.isInterrupted());
        }
    }
}
