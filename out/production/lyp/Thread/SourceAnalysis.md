## 构造方法   
    //间接调用最后一个构造函数，采用默认的拒绝策略AbortPolicy和默认的线程工厂
    ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue<Runnable>)
    //间接调用最后一个构造函数，采用默认的默认的线程工厂
    ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue<Runnable>, 
    RejectedExecutionHandler)
    //间接调用最后一个构造函数，采用默认的拒绝策略AbortPolicy
    ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue<Runnable>, ThreadFactory)
    //前面三个分别调用了最后一个
    ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue<Runnable>, ThreadFactory, RejectedExecutionHandler)
    //最后一个构造函数的具体实现
    public ThreadPoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue,
                                  ThreadFactory threadFactory,
                                  RejectedExecutionHandler handler) {
    //参数合法性检验，核心线程数目、最大线程数目、线程空闲回收时间不得小于0，最大线程池不得小于核心线程数数目
      if (corePoolSize < 0 ||maximumPoolSize <= 0 ||maximumPoolSize < corePoolSize ||keepAliveTime < 0)
          throw new IllegalArgumentException();
    //参数合法性检验，阻塞队列，线程工厂，决绝策略不得为空
      if (workQueue == null || threadFactory == null || handler == null)
                throw new NullPointerException();
      this.corePoolSize = corePoolSize;//核心线程数大小
      this.maximumPoolSize = maximumPoolSize;//最大线程数目
      this.workQueue = workQueue;//阻塞队列
      this.keepAliveTime = unit.toNanos(keepAliveTime);//空闲回收时间
      this.threadFactory = threadFactory;//线程工厂
      this.handler = handler;//拒绝策略
     }
## 首先大家要把这三个函数搞明白，几乎每个函数都会用到这几个函数： 
       runStateOf(int c) 是通过与的方式，在clt字段中获取到clt的前三位，也就是线程池的状态标识。 
       workerCountOf(int c)是通过与的方式，在clt字段中获取到clt的后29位，也就是线程池中的线程数量。 
       ctlOf(int rs, int wc) 是通过或的方式，将修改后的线程池状态rs和线程池中线程数量打包成clt。 
       isRunning(int c) SHUTDOWN的状态是0左移29为得到的，比他大的均是线程池停止或销毁状态！
       private static int runStateOf(int c)     { return c & ~CAPACITY; }
           private static int workerCountOf(int c)  { return c & CAPACITY; }
           private static int ctlOf(int rs, int wc) { return rs | wc; }
           private static boolean isRunning(int c) {
               return c < SHUTDOWN;
           }    
##  execute()
     if (command == null)
                throw new NullPointerException();
            int c = ctl.get();//AtomicInteger
           //(1)
            if (workerCountOf(c) < corePoolSize) {
                if (addWorker(command, true))
                    return;
                c = ctl.get();
            }
            //(2)
            if (isRunning(c) && workQueue.offer(command)) {
                int recheck = ctl.get();
                if (! isRunning(recheck) && remove(command))//（21）
                    reject(command);
                else if (workerCountOf(recheck) == 0)//（22）
                    addWorker(null, false);//为什么是false
            }//(3)
            else if (!addWorker(command, false))
                reject(command);
    （1）首先查看了当前线程池中的线程数量是否小于我们指定的核心线程池的数目，如果是就尝试新建一个线程，把command作为他的第一个任务，并把他们加入到线程池中。但是我们在判断了线程池的数量合法后，调用addWorker(command, true)把线程加入到线程池中时，是多线程并发的，可能会导致加入失败。如果加入成功，则直接返回，若假如失败，则重新获取clt，因为此时clt必发生了变化，否则不会失败，继续往下执行（2）。 
    （2）通过isRunning(c) 判断如果线程池还在运行，那我们就尝试把当前的command加入到阻塞队列中。加入的过程也是并发的，也可能会出现失败。如果失败在继续执行（3）。加入阻塞队列成功后我们要重新在检查一遍，防止在加入的过程中线程时关闭了或者线程池中没有线程了，全部因为空闲时间超过了我们指定的alivetime被回收了。如果是线程池已经不再是RUNNING状态，则用我们的拒绝策略去丢弃它（21）。如果是线程池没有了线程，那我们新建一个空线程，让他去阻塞队列中去获取任务执行（22）。 
    （3）如果上面的两步都没有执行成功，那我们此时就需要使用我们指定的最大线程池，来处理它，但是此时也是可能失败的，可能有多个线程执行么，如果失败，就用拒绝策略丢弃该线程。
## private boolean addWorker(Runnable firstTask, boolean core) {
          //（1）循环CAS操作，将线程池中的线程数+1.
          retry:
          for (;;) {
              int c = ctl.get();
              int rs = runStateOf(c);
  
              // Check if queue empty only if necessary.
  
              if (rs >= SHUTDOWN &&
                  ! (rs == SHUTDOWN &&
                     firstTask == null &&
                     ! workQueue.isEmpty()))
                  return false;
  
              for (;;) {
                  int wc = workerCountOf(c);
                  //core true代表是往核心线程池中增加线程 false代表往最大线程池中增加线程
                  //线程数超标，不能再添加了，直接返回
                  if (wc >= CAPACITY ||
                      wc >= (core ? corePoolSize : maximumPoolSize))
                      return false;
                  //CAS修改clt的值+1，在线程池中为将要添加的线程流出空间，成功退出cas循环，失败继续
                  if (compareAndIncrementWorkerCount(c))
                      break retry;
                  c = ctl.get();  // Re-read ctl
                  //如果线程池的状态发生了变化回到retry外层循环
                  if (runStateOf(c) != rs)
                      continue retry;
                  // else CAS failed due to workerCount change; retry inner loop
              }
          }
          //(2)新建线程，并加入到线程池workers中。
          boolean workerStarted = false;
          boolean workerAdded = false;
          Worker w = null;
          try {
              //对workers操作要通过加锁来实现
              final ReentrantLock mainLock = this.mainLock;
              w = new Worker(firstTask);
              final Thread t = w.thread;
              if (t != null) {
                 //细化锁的力度，防止临界区过大，浪费时间
                  mainLock.lock();
                  try {
                      // Recheck while holding lock.
                      // Back out on ThreadFactory failure or if
                      // shut down before lock acquired.
                      int c = ctl.get();
                      int rs = runStateOf(c);
                      //判断线程池的状态
                      if (rs < SHUTDOWN ||
                          (rs == SHUTDOWN && firstTask == null)) {
                          //判断添加的任务状态,如果已经开始丢出异常
                          if (t.isAlive()) // precheck that t is startable
                              throw new IllegalThreadStateException();
                         //将新建的线程加入到线程池中
                          workers.add(w);
                          int s = workers.size();
                          //修正largestPoolSize的值
                          if (s > largestPoolSize)
                              largestPoolSize = s;
                          workerAdded = true;
                      }
                  } finally {
                      mainLock.unlock();
                  }
                  //线程添加线程池成功，则开启新创建的线程
                  if (workerAdded) {
                      t.start();//(3)
                      workerStarted = true;
                  }
              }
          } finally {
              //线程添加线程池失败或者线程start失败，则需要调用addWorkerFailed函数，如果添加成功则需要移除，并回复clt的值
              if (! workerStarted)
                  addWorkerFailed(w);
          }
          return workerStarted;
      }
    可能会有人问，当时我也困惑，为什么把线程池中线程数目增加和加入到works中两部拆开，
    为什么不在+1之后立即执行新建线程并加入到works中的操作。我觉得是放到一块可阅读性比较差，
    而且（2）代码块中的try-catch-finnaly会变的不好控制。

## private Runnable getTask() {
           boolean timedOut = false; // Did the last poll() time out?
   
           retry:
           for (;;) {
               int c = ctl.get();
               int rs = runStateOf(c);
   
               //如果线程池处于shutdown状态，并且队列为空，或者线程池处于stop或者terminate状态，在线程池数量-1，返回null，回收线程
               if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                   decrementWorkerCount();
                   return null;
               }
   
               //标识当前线程在空闲时，是否应该超时回收
               boolean timed;    
   
               for (;;) {
                   int wc = workerCountOf(c);
                   //如果allowCoreThreadTimeOut 为ture或者当前线程数量大于核心线程池数目，则需要超时回收
                   timed = allowCoreThreadTimeOut || wc > corePoolSize;
                   //（1）
                   //如果线程数目小于最大线程数目，且不允许超时回收或者未超时，则跳出循环，继续去阻塞队列中取任务（2）
                   if (wc <= maximumPoolSize && ! (timedOut && timed))
                       break;
                   //如果上面if没有成立，则当前线程数-1，返回null，回收该线程
                   if (compareAndDecrementWorkerCount(c))
                       return null;
                   //如果上面if没有成立，则CAS修改ctl失败，重读，cas循环重新尝试修改
                   c = ctl.get();  // Re-read ctl
                   if (runStateOf(c) != rs)
                       continue retry;
                   // else CAS failed due to workerCount change; retry inner loop
               }
   
               （2）
               try {
               //如果允许空闲回收，则调用阻塞队列的poll，否则take，一直等到队列中有可取任务
                   Runnable r = timed ?
                       workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                       workQueue.take();
                   //取到任务，返回任务，否则超时timedOut = true;进入下一个循环,并且在（1）处会不成立，进而进入到cas修改ctl的程序中
                   if (r != null)
                       return r;
                   timedOut = true;
               } catch (InterruptedException retry) {
                   timedOut = false;
               }
           }
       }

## private boolean addWorker(Runnable firstTask, boolean core) {   添加新的线程
           retry:
           for (;;) {
               int c = ctl.get();
               int rs = runStateOf(c);
   
               // Check if queue empty only if necessary.
               if (rs >= SHUTDOWN &&
                   ! (rs == SHUTDOWN &&
                      firstTask == null &&
                      ! workQueue.isEmpty()))
                   return false;
   
               for (;;) {
                   int wc = workerCountOf(c);
                   //当前线程池的数量已经达到限定，不能添加新的线程了！
                   if (wc >= CAPACITY ||
                       wc >= (core ? corePoolSize : maximumPoolSize))
                       return false;
                   //cas修改增加线程池所拥有的线程数
                   if (compareAndIncrementWorkerCount(c))
                       break retry;
                   c = ctl.get();  // Re-read ctl
                   if (runStateOf(c) != rs)
                       continue retry;
                   // else CAS failed due to workerCount change; retry inner loop
               }
           }
   
           boolean workerStarted = false;
           boolean workerAdded = false;
           Worker w = null;
           try {
               final ReentrantLock mainLock = this.mainLock;
               //创建一个新的Worker，则封装了一个firstTask
               w = new Worker(firstTask);
               final Thread t = w.thread;
               if (t != null) {
                   mainLock.lock();
                   try {
                       // Recheck while holding lock.
                       // Back out on ThreadFactory failure or if
                       // shut down before lock acquired.
                       int c = ctl.get();
                       int rs = runStateOf(c);
   
                       if (rs < SHUTDOWN ||
                           (rs == SHUTDOWN && firstTask == null)) {
                           if (t.isAlive()) // precheck that t is startable
                               throw new IllegalThreadStateException();
                           //提交Worker到线程池所维护的workers集合中（可以认为这是一组线程）
                           workers.add(w);
                           int s = workers.size();
                           if (s > largestPoolSize)
                               largestPoolSize = s;
                           workerAdded = true;
                       }
                   } finally {
                       mainLock.unlock();
                   }
                   if (workerAdded) {
                       //启动线程，执行Worker执行的run实现
                       t.start();
                       workerStarted = true;
                   }
               }
           } finally {
               if (! workerStarted)
                   addWorkerFailed(w);
           }
           return workerStarted;
       }