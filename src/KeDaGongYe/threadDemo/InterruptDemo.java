package KeDaGongYe.threadDemo;

public class InterruptDemo {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Worker());
        t.start();
        t.interrupt();
        Thread.sleep(1000);
        System.out.println("Main thread stopped.");
    }

    public static class Worker implements Runnable {
        public void run() {
            System.out.println("Worker started.");
            System.out.println("Worker IsInterrupted before: " +
                    Thread.currentThread().isInterrupted());
            //Thread.interrupted();
            System.out.println("Worker IsInterrupted  after interrupted: " +
                    Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Worker IsInterrupted after: " +
                        Thread.currentThread().isInterrupted());
                System.out.println("************Worker stopped*************");
                return;
            }
        }
    }
}
