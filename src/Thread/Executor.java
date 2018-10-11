package Thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

public class Executor {

    //固定大小的线程池
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    //可缓存的线程池
    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    //创建一个固定长度线程池，支持定时及周期性任务执行。
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
//       提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
//       该 Future 的 get 方法在成功完成时将会返回给定的结果
        executor.submit(() -> {
            return null;
        });

        //1- 在未来某个时间执行给定的命令。
        // 该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            System.out.println(index);
                            Thread.sleep(10 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//(1) newCachedThreadPool
//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
//(2) newSingleThreadExecutor
//创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
//(3) newScheduledThreadPool
//创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
//(4)newFixedThreadPool
//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下