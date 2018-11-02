package Thread;

public class RunnableTask {
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("Hello " + threadName);
            }
        };
        task.run();
        Thread thread = new Thread(task);
        thread.start();


//        Runnable task = () -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println("Hello " + threadName);
//        };
//        task.run();
//        Thread thread = new Thread(task);
//        thread.start();


        Thread t = new Thread() {
            public void run() {
                System.out.println("exit");
            }
        };
        t.setDaemon(true);
    }
}
