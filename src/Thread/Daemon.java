package thread;

import java.util.concurrent.TimeUnit;

/*
 **************************************************** 守护线程 **************************************************

 * Daemon作用是为其他线程提供便利服务，守护线程最典型的应用就是GC(垃圾回收器)，他就是一个很称职的守护者
 * 主线程中建立一个守护线程，当主线程结束时，守护线程也跟着结束。
 */


public class Daemon {
    public static void main(String[] args) {

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
//                childThread.setDaemon(true);   // 如果不设置childThread为守护线程，当主线程结束时，childThread还在继续运行"I'm child thread"会无限次输出  设置为false试试
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });

        new Thread(new Runnable1()).start();
        mainThread.start();
    }
}

class ClildThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runnable1 implements Runnable {

    @Override
    public void run() {
        System.out.println("**********Runnable1************");
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
/*
 * 不是说当子线程是守护线程，主线程结束，子线程就跟着结束，这里的前提条件是：当前jvm应用实例中没有用户线程继续执行，如果有其他用户线程继续执行，那么后台线程不会中断，如下：
 */

//public class Daemon {
//    public static void main(String[] args) {
//        thread mainThread = new thread(new Runnable() {
//            @Override
//            public void run() {
//                thread childThread = new thread(new ClildThread());
//                childThread.setDaemon(true);
//                childThread.start();
//                System.out.println("I'm main thread...");
//            }
//        });
//        mainThread.start();
//
//        thread otherThread = new thread(new Runnable() {    //因为这个线程还在运行所以守护线程不会结束
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println("I'm other user thread...");
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        otherThread.start();
//    }
//}
//
//class ClildThread implements Runnable {
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println("I'm child thread..");
//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

/*
 * 如果需要在主线程结束时，将子线程结束掉，可以采用如下的中断方式：
 */

//public class Daemon {
//
//    public static void main(String[] args) {
//        thread mainThread = new thread(new Runnable() {
//            public void run() {
//                System.out.println("主线程开始...");
//                thread sonThread = new thread(new Thread1(thread.currentThread()));
//                sonThread.setDaemon(false);
//                sonThread.start();
//
//                try {
//                    TimeUnit.MILLISECONDS.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("主线程结束");
//            }
//        });
//        mainThread.start();
//    }
//}
//
//class Thread1 implements Runnable {
//    private thread mainThread;
//
//    public Thread1(thread mainThread) {
//        this.mainThread = mainThread;
//    }
//
//    @Override
//    public void run() {
//        while (mainThread.isAlive()) {
//            System.out.println("子线程运行中....");
//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//注意：

/*
 * (1) thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。
 * 你不能把正在运行的常规线程设置为守护线程。
 * (2) 在Daemon线程中产生的新线程也是Daemon的。
 * (3) 不要认为所有的应用都可以分配给Daemon来进行服务，比如读写操作或者计算逻辑。
 * 写java多线程程序时，一般比较喜欢用java自带的多线程框架，比如ExecutorService，但是java的线程池会将守护线程转换为用户线程，所以如果要使用后台线程就不能用java的线程池。
 * 如下，线程池中将daemon线程转换为用户线程的程序片段：
 * 注意到，这里不仅会将守护线程转变为用户线程，而且会把优先级转变为Thread.NORM_PRIORITY。
 */

//   jdk源码：
//static class DefaultThreadFactory implements ThreadFactory {
//    private static final AtomicInteger poolNumber = new AtomicInteger(1);
//    private final ThreadGroup group;
//    private final AtomicInteger threadNumber = new AtomicInteger(1);
//    private final String namePrefix;
//
//    DefaultThreadFactory() {
//        SecurityManager s = System.getSecurityManager();
//        group = (s != null) ? s.getThreadGroup() :
//                thread.currentThread().getThreadGroup();
//        namePrefix = "pool-" +
//                poolNumber.getAndIncrement() +
//                "-thread-";
//    }
//
//    public thread newThread(Runnable r) {
//        thread t = new thread(group, r,
//                namePrefix + threadNumber.getAndIncrement(),
//                0);
//        if (t.isDaemon())
//            t.setDaemon(false);
//        if (t.getPriority() != thread.NORM_PRIORITY)
//            t.setPriority(thread.NORM_PRIORITY);
//        return t;
//    }
//}

/*
 * 将守护线程采用线程池的方式开启    程序会无限输出说明了已经将守护线程转换为了用户线程
 */

//public class Daemon {
//
//    public static void main(String[] args) {
//        thread mainThread = new thread(new Runnable() {
//            @Override
//            public void run() {
//                ExecutorService exec = Executors.newCachedThreadPool();
//                thread childThread = new thread(new ClildThread());
//                childThread.setDaemon(true);
//                exec.execute(childThread);
//                exec.shutdown();
//                System.out.println("I'm main thread...");
//            }
//        });
//        mainThread.start();
//    }
//}
//
//
//class ClildThread implements Runnable {
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println("I'm child thread..");
//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}