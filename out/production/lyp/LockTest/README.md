## Java对象头
  在JVM中，对象在内存中的布局分为三块区域：对象头、实例数据和对齐填充
### 实例变量：
  存放类的属性数据信息，包括父类的属性信息，如果是数组的实例部分还包括数组的长度，这部分内存按4字节对齐。
### 填充数据：
  由于虚拟机要求对象起始地址必须是8字节的整数倍。填充数据不是必须存在的，仅仅是为了字节对齐，这点了解即可。
### 对象头：
对象头实现synchronized锁对象的基础，这点我们重点分析它，一般而言，synchronized使用的锁对象是存储在Java对象头里的，jvm中采用2个字来存储对象头(如果对象是数组则会分配3个字，多出来的1个字记录的是数组长度)，其主要结构是由Mark Word 和 Class Metadata Address 组成，其结构说明如
## JDK 对锁进行的优化：
### 1.锁粗化（Lock Coarsening）：
  也就是减少不必要的紧连在一起的unlock，lock操作，将多个连续的锁扩展成一个范围更大的锁。
### 2.锁消除（Lock Elimination）：
  通过运行时JIT编译器的逃逸分析来消除一些没有在当前同步块以外被其他线程共享的数据的锁保护，通过逃逸分析也可以在线程本地Stack上进行对象空间的分配（同时还可以减少Heap上的垃圾收集开销）。
### 3.轻量级锁（Lightweight Locking）：
  这种锁实现的背后基于这样一种假设，即在真实的情况下我们程序中的大部分同步代码一般都处于无锁竞争状态（即单线程执行环境），在无锁竞争的情况下完全可以避免调用操作系统层面的重量级互斥锁，取而代之的是在monitorenter和monitorexit中只需要依靠一条CAS原子指令就可以完成锁的获取及释放。当存在锁竞争的情况下，执行CAS指令失败的线程将调用操作系统互斥锁进入到阻塞状态，当锁被释放的时候被唤醒（具体处理步骤下面详细讨论）。
### 4.偏向锁（Biased Locking）：
  是为了在无锁竞争的情况下避免在锁获取过程中执行不必要的CAS原子指令，因为CAS原子指令虽然相对于重量级锁来说开销比较小但还是存在非常可观的本地延迟。
### 5.适应性自旋（Adaptive Spinning）：当线程在获取轻量级锁的过程中执行CAS操作失败时，在进入与monitor相关联的操作系统重量级锁（mutex semaphore）前会进入忙等待（Spinning）然后再次尝试，当尝试一定的次数后如果仍然没有成功则调用与该monitor关联的semaphore（即互斥锁）进入到阻塞状态。


lock在获取锁的过程可以被中断。
lock可以尝试获取锁tryLock()，如果锁被其他线程持有，则返回 false，不会使当前线程休眠。
tryLock()：尝试获得锁，如果成功，返回 true，否则，返回 false。
tryLock(long time,TimeUnit unit)：lock在尝试获取锁的时候，传入一个时间参数，如果在这个时间范围内，没有获得锁，那么就是终止请求。

    
    Synchorized 和reentrylock 区别：
    1.re  请求锁可中断
    2.Re 可以设置为非公平模式
    3.可以使用非阻塞的模式获取锁
    4.就是Synchorized 中只能和一组wait  和 notifyall同时存在 但是re就可以绑定多个condition
    
    
    
    3.java 中锁的种类及辨析：
    简述 synchronized 和 java.util.concurrent.locks.Lock 的异同？
    主要相同点： Lock 能完成 synchronized 所实现的所有功能
    主要不同点： Lock 有比 synchronized 更精确的线程语义和更好的性能。 synchronized 会自动释放锁，而 Lock 一定要求程序员手工释放，并且必须在 finally 从句中释放。 Lock 还有更强大的功能，例如，它的 tryLock 方法可以非阻塞方式去拿锁。 
    当线程调用同步方法时，它自动获得这个方法所在对象的内在锁，并且方法返回时释放锁，如果发生未捕获的异常，也会释放锁。
    当调用静态同步方法时，因为静态方法和类相关联，线程获得和这个类关联的Class对象的内在锁。
    1.同步锁:synchronized 关键字   非公平锁 可重入 Java提供了synchronized关键字来支持内在锁.每个锁都关联一个请求计数器和一个占有他的线程，当请求计数器为0时，这个锁可以被认为是unhled的，当一个线程请求一个unheld的锁时，JVM记录锁的拥有者，并把锁的请求计数加1，如果同一个线程再次请求这个锁时，请求计数器就会增加，当该线程退出syncronized块时，计数器减1，当计数器为0时，锁被释放。
    1. 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象； 
    2. 修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象； 
    3. 修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象； 
    4. 修饰一个类，其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象。
    2.并发锁：  可重入   公平锁（可以设置为不公平） 可重入 
    ReentrantLock实现了Lock接口  aqs
    ReentrantLock，同一时间内只有一个线程可以获取这个锁并占用资源。其他线程想要获取锁，必须等待这个线程释放锁我们可以在创建ReentrantLock对象时，通过以下方式来设置锁的公平性：
    在初始化ReentrantLock的时候，如果我们不传参数是否公平，那么默认使用非公平锁，也就是NonfairSync。
    ReentrantLock lock = new ReentrantLock(true); 
    isFair()        //判断锁是否是公平锁
    isLocked()    //判断锁是否被任何线程获取了
    isHeldByCurrentThread()   //判断锁是否被当前线程获取了
    hasQueuedThreads()   //判断是否有线程在等待该锁
    lock()  阻塞式地获取锁，只有在获取到锁后才处理interrupt信息
    lockInterruptibly() 阻塞式地获取锁，立即处理interrupt信息，并抛出异常
    tryLock（）尝试获得锁，不管成功失败，都立即返回true、false
    tryLock(long timeout, TimeUnit unit)在timeout时间内阻塞式地获取锁，成功返回true，超时返回false，同时立即处理interrupt信息，并抛出异常
    同步锁和并发锁的区别：
    （1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
    （2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
    （3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
    （4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到
    （5）Lock可以提高多个线程进行读操作的效率
    
    
    3.自旋锁：
    spinlock 不会有线程状态切换，所以响应更快。
    非公平锁
    非可重入锁 
    benfit：响应速度更快， 因为不切换线程状态 *7
    bad:线程数量达到一定量时， 性能下降
    public class SpinLock implements Lock{ 
    private AtomicReference<Thread> sign = new AtomicReference<Thread>();
    public void lock(){ Thread current=Thread.currentThread(); 
    while(!sign.compareAndSet(null, current)){ 
    } 
    }
    public void unlock(){ 
    Thread current=Thread.currentThread(); 
    sign.compareAndSet(current, null); 
    } 
    }
    
    4.读写锁：
    写少读多的场景:读和写是互斥的，读操作时并发的
    它可以允许多个线程读取资源，但是只能允许一个线程写入资源
    读写锁将对一个资源（比如文件）的访问分成了2个锁，一个读锁和一个写锁。
    ReentrantReadWriteLock 和 ReentrantLock 不是继承关系，但都是基于 AbstractQueuedSynchronizer 来实现。
    ReadWriteLock就是读写锁，它是一个接口，ReentrantReadWriteLock实现了这个接口。
    可以通过readLock()获取读锁，通过writeLock()获取写锁
    线程进入读锁的前提条件：
        没有其他线程的写锁，
        没有写请求或者有写请求，但调用线程和持有锁的线程是同一个
    不能先获得读锁再获得写锁
    线程进入写锁的前提条件：
        没有其他线程的读锁
        没有其他线程的写锁
    
    读取锁可以同时分配给多个reader线程
    写入锁只能分配给一个writer线程。
    注意3：不仅读锁的是可递归的，写锁也是可递归的
    此锁最多支持 65535 个递归写入锁和 65535 个读取锁。试图超出这些限制将导致锁方法抛出 Error
    n=n0+n1+n2=n1+2Xn2+1