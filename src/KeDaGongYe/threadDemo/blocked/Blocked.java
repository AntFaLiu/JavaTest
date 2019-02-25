package keDaGongYe.threadDemo.blocked;


public class Blocked {
    public static Object obj = new Object();

    public static void main(String[] args) {

        new Thread(new Worker(), "Worker 1").start();
        //这个时候Worker 2 就处于阻塞状态
        new Thread(new Worker(), "Worker 2").start();
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            synchronized (obj) {
                System.out.println("thread name:" + Thread.currentThread().getName()
                        + "synchronized");
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
