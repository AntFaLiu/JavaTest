
## java实现多线程的几种方式

## 原子性

## java 内存模型

## 原子性
# Java 多线程
    java实现多线程的几种方式

## java 内存模型

## 原子性
    1、总线锁保证原子性
    使用处理器提供的一个LOCK#信号，当一个处理器在总线上输出此信号时，其它处理器的请求将被阻塞，那么该处理器就能独自共享内存。  
    2、缓存锁保证原子性
    “缓存锁定”指内存区域如果被缓存在处理器的缓存行中，并且在Lock操作期间被锁定，那么当它执行锁操作回写到内存时，处理器不需要在总线上声言LOCK#信号，而是修改内部的内存地址，通过缓存一致性机制保证操作的原子性。
    例外：当操作的数据不能被缓存在处理器内部，或操作的数据跨多个缓存行，处理器会调用总线锁定。

## Java中实现原子操作：
        使用循环CAS和锁的方式来实现

## Java如何实现原子操作：

    (1)使用循环CAS实现原子操作

    JVM中的CAS操作正是利用了处理器提供的CMPXCHG指令实现的，自旋的CAS实现的基本思路就是循环进行CAS操作直到成功为止。

## CAS
### jvm中的CAS操作是基于处理器的CMPXCHG指令实现的，CAS存在三个问题：

ABA问题
循环时间长开销大
只能保证一个共享变量的原子操作
锁
锁机制保证了只有获得锁的线程才能操作锁定的内存区域，具体实现可以参考java synchronized

3.使用锁机制实现原子操作

锁机制保证了只有获得锁的线程才能够操作锁定的内存区域。JVM内部实现了很多锁机制，有偏向锁，轻量级锁和互斥锁有意思的是除了偏向锁，JVM实现锁的方式都用了循环CAS，即当一个线程向进入同步块的时候使用CAS的方式来获取锁，当它退出同步块的是很好使用循环CAS释放锁。


##  Java 线程的 6 中状态  及其转换方法   ：NEW,RUNNABLE,BLOCKED,WAITING,TIMED_WAITING,TERMINATED;
public enum State {
        /**
         * Thread state for a thread which has not yet started.
         */
        NEW,

        /**
         * Thread state for a runnable thread.  A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
         */
        RUNNABLE,

        /**
         * Thread state for a thread blocked waiting for a monitor lock.
         * A thread in the blocked state is waiting for a monitor lock
         * to enter a synchronized block/method or
         * reenter a synchronized block/method after calling
         * {@link Object#wait() Object.wait}.
         */
        BLOCKED,   //等待锁
        /**
         * Thread state for a waiting thread.
         * A thread is in the waiting state due to calling one of the
         * following methods:
         * <ul>
         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
         *   <li>{@link #join() Thread.join} with no timeout</li>
         *   <li>{@link LockSupport#park() LockSupport.park}</li>
         * </ul>
         * <p>A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         * For example, a thread that has called <tt>Object.wait()</tt>
         * on an object is waiting for another thread to call
         * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
         * that object. A thread that has called <tt>Thread.join()</tt>
         * is waiting for a specified thread to terminate.
         */
        WAITING,
        /**
         * Thread state for a waiting thread with a specified waiting time.
         * A thread is in the timed waiting state due to calling one of
         * the following methods with a specified positive waiting time:
         * <ul>
         *   <li>{@link #sleep Thread.sleep}</li>
         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
         *   <li>{@link #join(long) Thread.join} with timeout</li>
         *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
         * </ul>
         */
        TIMED_WAITING
        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         */
        TERMINATED;
}


借鉴：https://www.zhihu.com/question/27654579

初始态：NEW创建一个Thread对象，但还未调用start()启动线程时，线程处于初始态。运行态：RUNNABLE在Java中，
运行态包括就绪态 和 运行态。
就绪态  该状态下的线程已经获得执行所需的所有资源，只要CPU分配执行权就能运行。所有就绪态的线程存放在就绪队列中。
运行态  获得CPU执行权，正在执行的线程。由于一个CPU同一时刻只能执行一条线程，
因此每个CPU每个时刻只有一条运行态的线程。
阻塞态当一条正在执行的线程请求某一资源失败时，就会进入阻塞态。而在Java中，阻塞态专指请求锁失败时进入的状态。
由一个阻塞队列存放所有阻塞态的线程。处于阻塞态的线程会不断请求资源，一旦请求成功，就会进入就绪队列，等待执行。
PS：锁、IO、Socket等都资源。等待态当前线程中调用wait、join、park函数时，当前线程就会进入等待态。
也有一个等待队列存放所有等待态的线程。线程处于等待态表示它需要等待其他线程的指示才能继续运行。
进入等待态的线程会释放CPU执行权，并释放资源（如：锁）
超时等待态当运行中的线程调用sleep(time)、wait、join、parkNanos、parkUntil时，就会进入该状态；
它和等待态一样，并不是因为请求不到资源，而是主动进入，并且进入后需要其他线程唤醒；进入该状态后释放CPU执行权 
和 占有的资源。
与等待态的区别：到了超时时间后自动进入阻塞队列，开始竞争锁。终止态线程执行结束后的状态。

注意：
wait()方法会释放CPU执行权 和 占有的锁。sleep(long)方法仅释放CPU使用权，锁仍然占用；线程被放入超时等待队列，与yield相比，它会使线程较长时间得不到运行。
yield()方法仅释放CPU执行权，锁仍然占用，线程会被放入就绪队列，会在短时间内再次执行。wait和notify必须配套使用，即必须使用同一把锁调用；
wait和notify必须放在一个同步块中调用wait和notify的对象必须是他们所处同步块的锁对象



内存屏障，又称内存栅栏，是一组处理器指令，用于实现对内存操作的顺序限制。
为了阻止某些特定情况下的重排序
内存屏障的种类
几乎所有的处理器至少支持一种粗粒度的屏障指令，通常被称为“栅栏（Fence）”，它保证在栅栏前初始化的load和store指令，能够严格有序的在栅栏后的load和store指令之前执行。无论在何种处理器上，这几乎都是最耗时的操作之一（与原子指令差不多，甚至更消耗资源），所以大部分处理器支持更细粒度的屏障指令。

内存屏障的一个特性是将它们运用于内存之间的访问。尽管在一些处理器上有一些名为屏障的指令，但是正确的/最好的屏障使用取决于内存访问的类型。下面是一些屏障指令的通常分类，正好它们可以对应上常用处理器上的特定指令（有时这些指令不会导致操作）。

LoadLoad 屏障

序列：Load1,Loadload,Load2
    确保Load1所要读入的数据能够在被Load2和后续的load指令访问前读入。
    通常能执行预加载指令或/和支持乱序处理的处理器中需要显式声明Loadload屏障，
    因为在这些处理器中正在等待的加载指令能够绕过正在等待存储的指令。 
    而对于总是能保证处理顺序的处理器上，设置该屏障相当于无操作。

StoreStore  屏障

序列：Store1，StoreStore，Store2

确保Store1的数据在Store2以及后续Store指令操作相关数据之前对其它处理器可见（例如向主存刷新数据）。
通常情况下，如果处理器不能保证从写缓冲或/和缓存向其它处理器和主存中按顺序刷新数据，那么它需要使用StoreStore屏障。

LoadStore 屏障

序列： Load1; LoadStore; Store2

确保Load1的数据在Store2和后续Store指令被刷新之前读取。
在等待Store指令可以越过loads指令的乱序处理器上需要使用LoadStore屏障。

StoreLoad Barriers  开销最大

序列: Store1; StoreLoad; Load2

    确保Store1的数据在被Load2和后续的Load指令读取之前对其他处理器可见。
    StoreLoad屏障可以防止一个后续的load指令 不正确的使用了Store1的数据，
    而不是另一个处理器在相同内存位置写入一个新数据。正因为如此，
    所以在下面所讨论的处理器为了在屏障前读取同样内存位置存过的数据，
    必须使用一个StoreLoad屏障将存储指令和后续的加载指令分开。Storeload
    屏障在几乎所有的现代多处理器中都需要使用，
    但通常它的开销也是最昂贵的。它们昂贵的部分原因是它们必须关闭通常的略过缓存直接从写缓冲区读取数据的机制。
    这可能通过让一个缓冲区进行充分刷新（flush）,以及其他延迟的方式来实现。

### volatile 关键字
Volatile
#### 1、保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
#### 2、禁止进行指令重排序。

####（1）New新建状态，建立了线程对象但是还未启动。
####（2）Runnable 线程已经启动了，在 wait blocked pool 里准备被执行。（同一时间单核心CPU只能运行一条线程，当运行的线程消耗光自己的时间片后任务调度系统将在等待池中随机选中一个合适的线程运行它）
####（3）Running 当前线程被任务调度系统选中，正处于被执行状态。
####（4）Blocked 当前线程遇到锁或者一些其他事件被阻塞挂起，会将当前线程放入lock blocked pool中。当锁释放或者一些特定条件被触发，线程从阻塞状态转换到可运行状态，该线程将会被放入wait blocked pool在下次调度的时候可能被运行。
####（5）Stop 线程结束运行。

## ThreadPool
### newFixedThreadPool()
### newSingleThreadExecutor()
### newCachedThreadPool()
### newScheduledThreadPool()
### ThreadPoolExecutor()


    corePoolSize ：核心线程数量，就是要保持在线程池中即使不运行任务也一直存活的线程数量。值得注意的是如果当前池中有空闲线程，但是总线程数没有达到corePoolSize 设置的值时优先开启新的线程直到到达corePoolSize为止。
    
    maximumPoolSize：线程池运许存在的最大线程数量（包括核心线程），其运行规则是：
    
    提交一个新的任务，如果线程数量没有达到corePoolSize的限制则创建新的线程执行
    如果达到当前正在执行任务的线程数量等于corePoolSize，那么将新添加的任务放到workQueue中等待其他线程空闲出来后再队列中取出任务继续执行
    如果workQueue已经满了且corePoolSize < currentPoolSize < maximumPoolSize
    时会创建新的线程，辅助核心线程加速执行任务
    如果workQueue已满且currentPoolSize = maximumPoolSize 时则抛出异常拒绝接受新的任务
    keepAliveTime & unit：这两个时配合使用的，keepAliveTime用于控制currentPoolSize > corePoolSize时多出来的线程运许空闲的最大时间。unit 为时间单位（TimeUnit）
    workQueue ： 一个阻塞的任务队列。根据不同实现此队列有所不同。
## 锁
### synchronized
#### 修饰代码块
#### 修饰方法



## 3.Volatile：
    例子：假如count变量是volatile的。线程1，线程2 在进行read,load 操作中，发现主内存中count的值都是5，那么都会加载这个最新的值在线程1堆count进行修改之后，会write到主内存中，主内存中的count变量就会变为6，线程2由于已经进行read,load操作，在进行运算之后，也会更新主内存count的变量值为6，导致两个线程及时用volatile关键字修改之后，还是会存在并发的情况。
    解决的问题：
    每次让线程从内存中去取数据。
    Jvm的重排序

    线程池中的参数：
    corepoolSize       //核心线程数
    Maxmumpoolsize     //最大线程数
    keepAliveTime      //闲置线程存活时间
    NewlinkedBlockingDeque<Runnable>();// 任务队列
    Executors.defaultThreadFatory() //线程工厂         
    New AbortPolicy()// 队列已满而且当前线程数超过最大线程数时的异常处理策略
    BlockingQueue  中都定义了一个  ReentrantLock
    其中的方法都是同步的 
    public ThreadPoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue) {
    public PriorityBlockingQueue() {    //添加任务的方法和Queue相同
            q = new PriorityQueue<E>();           
    }
    阻塞队列可以很容易的实现数据共享  
    ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。	fifo
    LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。Fifo
    PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。Fifo 基于优先级队列实现
    DelayQueue：一个使用优先级队列实现的无界阻塞队列。基于优先级队列实现	fifo
    SynchronousQueue：一个不存储元素的阻塞队列。
    LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
    LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
    声明一个SynchronousQueue有两种不同的方式，它们之间有着不太一样的行为。公平模式和非公平模式的区别:
    如果采用公平模式：SynchronousQueue会采用公平锁，并配合一个FIFO队列来阻塞多余的生产者和消费者，从而体系整体的公平策略；
    但如果是非公平模式（SynchronousQueue默认）：SynchronousQueue采用非公平锁，同时配合一个LIFO队列来管理多余的生产者和消费者，而后一种模式，如果生产者和消费者的处理速度有差距，则很容易出现饥渴的情况，即可能有某些生产者或者是消费者的数据永远都得不到处理。

    临界资源：同一时刻只允许一个线程访问的资源
    临界区 ：访问临界资源的代码段
    临界区特点：临界区（Critical Section）是一段供线程独占式访问的代码，也就是说若有一线程正在访问该代码段，其它线程想要访问，只能等待当前线程离开该代码段方可进入，这样保证了线程安全。他工作于用户级（相对于内核级），在Window系统中CRITICAL_SECTION实现临界区相关机制。
    可重入函数：一个函数在执行的过程中被中断而在之后的某个时刻又重新执行这个任务，并且最后得到的结果与原本不被打断所得到的结果相同。
    可重入锁：外层函数在获得锁进行执行的之后，内层函数执行的时候仍然需要获得锁这时操作仍然可以实行这个锁就是可以重入的。
    修饰方法
    修饰方法

## 线程池
    1、降低资源消耗 
    可以重复利用已创建的线程降低线程创建和销毁造成的消耗。 
    2、提高响应速度 
    当任务到达时，任务可以不需要等到线程创建就能立即执行。 
    3、提高线程的可管理性 
    线程是稀缺资源，如果无限制地创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一分配、调优和监控

## 工作原理：
    1、线程池判断核心线程池里的线程是否都在执行任务。如果不是，则创建一个新的工作线程来执行任务。如果核心线程池里的线程都在执行任务，则执行第二步。 
    2、线程池判断工作队列是否已经满。如果工作队列没有满，则将新提交的任务存储在这个工作队列里进行等待。如果工作队列满了，则执行第三步 
    3、线程池判断线程池的线程是否都处于工作状态。如果没有，则创建一个新的工作线程来执行任务。如果已经满了，则交给饱和策略来处理这个任务

### 饱和策略：
    1.Abort策略：默认策略，新任务提交时直接抛出未检查的异常RejectedExecutionException，该异常可由调用者捕获。在之上的代码中已写。
    程序抛出了RejectedExecutionException，并且一共运行了8个任务(线程池开始能运行3个任务，工作队列中存储5个队列)。
    当工作队列满了的时候，直接抛出了异常，而且JVM一直不退出。我们可以看到执行任务的线程全是线程池中的线程。
    2.CallerRuns策略：为调节机制，既不抛弃任务也不抛出异常，而是将某些任务回退到调用者。不会在线程池的线程中执行新的任务，而是在调用exector的线程中运行新的任务。
    所有的任务都被运行，且有2（10 - 3 -5）个任务是在main线程中执行成功的，8个任务在线程池中的线程执行的。
    也有可能main线程只执行一个，因为可能出现最后一个加入线程池的时候，第一个已经执行完毕。
    3.Discard策略：新提交的任务被抛弃。通过上面的结果可以显示：没有异常抛出，后面提交的2个新任务被抛弃，只处理了前8（3+5）个任务，JVM退出.
    4.DiscardOldest策略：队列的是“队头”的任务，然后尝试提交新的任务。（不适合工作队列为优先队列场景）
