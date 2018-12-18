package KeDaGongYe.threadDemo;

class Test implements Runnable {
    boolean flag;
    int i = 0;
    @Override
    public void run() {
        try {
            while (true){
                System.out.println(i++);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("线程中断");
            return;
        }
    }
}

public class InterruptedTest {
    public static void main(String[] args) {
        Test a = new Test();
        a.flag = true;
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
