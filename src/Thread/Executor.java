package Thread;


import java.util.concurrent.*;

/**
 * Java 线程池
 */

/*
 * 在了解将任务提交给线程池到任务执行完毕整个过程之前，我们先来看一下ThreadPoolExecutor类中其他的一些比较重要成员变量：
 * private final BlockingQueue<Runnable> workQueue;              //任务缓存队列，用来存放等待执行的任务
 * private final ReentrantLock mainLock = new ReentrantLock();   //线程池的主要状态锁，对线程池状态（比如线程池大小、runState等）的改变都要使用这个锁
 * private final HashSet<Worker> workers = new HashSet<Worker>();//用来存放工作集
 * private volatile long  keepAliveTime;    //线程存货时间
 * private volatile boolean allowCoreThreadTimeOut;              //是否允许为核心线程设置存活时间
 * private volatile int   corePoolSize;                          //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
 * private volatile int   maximumPoolSize;                       //线程池最大能容忍的线程数
 * private volatile int   poolSize;                              //线程池中当前的线程数
 * private volatile RejectedExecutionHandler handler;            //任务拒绝策略
 * private volatile ThreadFactory threadFactory;                 //线程工厂，用来创建线程
 * private int largestPoolSize;                                  //用来记录线程池中曾经出现过的最大线程数
 * private long completedTaskCount;                              //用来记录已经执行完毕的任务个数
 * */

/*
 * public static ExecutorService newFixedThreadPool(int nThreads)
 *       创建固定数目线程的线程池。
 * public static ExecutorService newCachedThreadPool()
 *        创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
 * public static ExecutorService newSingleThreadExecutor()
 *        创建一个单线程化的Executor。
 * public static ScheduledExecutorService newScheduledThreadPool(int
 *        corePoolSize)
 *        创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。
 *       ExecutoreService提供了submit()方法，传递一个Callable，或Runnable，返回Future。如果Executor后台线程池还没有完成Callable的计算，这调用返回Future对象的get()方法，会阻塞直到计算完成。
 */

//public class Executor {
//
//    //固定大小的线程池
//    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
//    //可缓存的线程池
//    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    //创建一个固定长度线程池，支持定时及周期性任务执行。
//    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//    //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
//    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//
//    public static void main(String[] args) {
////       提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
////       该 Future 的 get 方法在成功完成时将会返回给定的结果
//        executor.submit(() -> {
//            return null;
//        });
//
//        //1- 在未来某个时间执行给定的命令。
//        // 该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。
//        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                public void run() {
//                    try {
//                        while (true) {
//                            System.out.println(index);
//                            Thread.sleep(10 * 1000);
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//execute只能接受Runnable类型的任务
//submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用Future的get()获得的还是null

//(1) newCachedThreadPool
//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
//(2) newSingleThreadExecutor
//创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
//(3) newScheduledThreadPool
//创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
//(4)newFixedThreadPool
//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下

//  execute只能接受Runnable类型的任务
//  submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用Future的get()获得的还是null
//  （2）返回值
//  由Callable和Runnable的区别可知：
//  execute没有返回值
//  submit有返回值，所以需要返回值的时候必须使用submit
//  （3）异常
//  1.execute中抛出异常
//  execute中的是Runnable接口的实现，所以只能使用try、catch来捕获CheckedException，通过实现UncaughtExceptionHande接口处理UncheckedException
//  即和普通线程的处理方式完全一致
//  2.submit中抛出异常
//  不管提交的是Runnable还是Callable类型的任务，如果不对返回值Future调用get()方法，都会吃掉异常

//public class Executor {
//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        ExecutorService executorSingle = Executors.newSingleThreadExecutor();
//        ExecutorService executorFixed = Executors.newFixedThreadPool(2);
//        ExecutorService executorScheduled = Executors.newScheduledThreadPool(5);
//        executorScheduled.submit(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5; i++) {
//                    System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
//                }
//            }
//        });
//        executorScheduled.shutdown();
//        executor.execute(new DemoRunnable());
//        executorSingle.submit(new DemoRunnable());
//        executor.submit(new DemoRunnable());
//        executorFixed.submit(new DemoRunnable());
//        Future<Boolean> future = executor.submit(new CallableTask());
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        executor.shutdown();//必须不能忘，否则会一致卡在那里
//    }
//}
//
//class DemoRunnable implements Runnable {
//    @Override
//    public void run() {
//         TODO Auto-generated method stub
//        System.out.println(Thread.currentThread().getName() + "-->我是通过实现接口的线程实现方式！");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
//        }
//        //int num = 3 / 0;
//        //System.out.println(Thread.currentThread().getName() + num);
//    }
//}
//
//class CallableTask implements Callable<Boolean> {
//    public Boolean call() throws Exception {
////		InputStream in = new FileInputStream(new File("xx.pdf"));
//        int num = 3 / 0;
//        return false;
//    }
//}

public class Executor {

    public static class MyCallable implements Callable {
        private int flag = 0;

        public MyCallable(int flag) {
            this.flag = flag;
        }

        public String call() throws Exception {
            if (this.flag == 0) {
                return "flag = 0";
            }
            if (this.flag == 1) {
                try {
                    while (true) {
                        System.out.println("looping.");
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                return "false";
            } else {
                throw new Exception("Bad flag value!");
            }
        }
    }

    public static void main(String[] args) {
        // 定义3个Callable类型的任务
        MyCallable task1 = new MyCallable(0);
        MyCallable task2 = new MyCallable(1);
        MyCallable task3 = new MyCallable(2);
        // 创建一个执行任务的服务
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {
            // 提交并执行任务，任务启动时返回了一个Future对象，
            // 如果想得到任务执行的结果或者是异常可对这个Future对象进行操作
            Future future1 = es.submit(task1);
            // 获得第一个任务的结果，如果调用get方法，当前线程会等待任务执行完毕后才往下执行
            System.out.println("task1: " + future1.get());
            Future future2 = es.submit(task2);
            // 等待5秒后，再停止第二个任务。因为第二个任务进行的是无限循环
            Thread.sleep(5000);
            System.out.println("task2 cancel: " + future2.cancel(true));
            // 获取第三个任务的输出，因为执行第三个任务会引起异常
            // 所以下面的语句将引起异常的抛出
            Future future3 = es.submit(task3);
            System.out.println("task3: " + future3.get());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        // 停止任务执行服务
        es.shutdownNow();
    }
}
// 关闭线程池:
//  线程池使用完毕，需要对其进行关闭，有两种方法
//  shutdown()
//  说明：shutdown并不是直接关闭线程池，而是不再接受新的任务…如果线程池内有任务，那么把这些任务执行完毕后，关闭线程池
//  shutdownNow()


// TODO 综合使用案例(FutureTask)

class CallDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // TODO 第一种方式:Future + ExecutorService
//        Task task = new Task();
//        ExecutorService service = Executors.newCachedThreadPool();
//        Future<Integer> future = service.submit(task);
//        System.out.println("result is " + future.get());
//        service.shutdown();



        // TODO 第二种方式: FutureTask + ExecutorService
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        System.out.println("result is " + futureTask.get());
        executor.shutdown();


        // TODO 第三种方式:FutureTask + Thread

        // 2. 新建FutureTask,需要一个实现了Callable接口的类的实例作为构造函数参数
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
//        // 3. 新建Thread对象并启动
//        Thread thread = new Thread(futureTask);
//        thread.setName("Task thread");
//        thread.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
//
//        // 4. 调用isDone()判断任务是否结束
//        if (!futureTask.isDone()) {
//            System.out.println("Task is not done");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        int result = 0;
//        try {
//            // 5. 调用get()方法获取任务结果,如果任务没有执行完成则阻塞等待
//            result = futureTask.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("result is " + result);

    }

    // 1. 继承Callable接口,实现call()方法,泛型参数为要返回的类型
    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
            int result = 0;
            for (int i = 0; i < 100; ++i) {
                result += i;
            }

            Thread.sleep(3000);
            return result;
        }
    }
}