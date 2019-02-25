package keDaGongYe.threadDemo.function;

public class FunctionTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(), "线程1......");
        Thread t2 = new Thread(new Worker(), "线程2......");
//        t1.setPriority(thread.NORM_PRIORITY+2);
        t2.setPriority(Thread.NORM_PRIORITY+3);
        t1.start();
        //t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1.isAlive: " + t1.isAlive());
    }
}

class Worker implements Runnable {
    public static Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
            System.out.println("thread name: " + Thread.currentThread().getName());
            System.out.println("isAlive: " + Thread.currentThread().isAlive());
            System.out.println("holdsLock: " + Thread.currentThread().holdsLock(obj));

            System.out.println(Thread.currentThread().getName() + "Priority: " + Thread.currentThread().getPriority());
            Thread.currentThread().dumpStack();
        }
    }
}