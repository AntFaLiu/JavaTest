package thread;

public class addWorker {
}
  /**
   * Checks if a new worker can be added with respect to current
   * pool state and the given bound (either core or maximum). If so,
   * the worker count is adjusted accordingly, and, if possible, a
   * new worker is created and started, running firstTask as its
   * first task. This method returns false if the pool is stopped or
   * eligible to shut down. It also returns false if the thread
   * factory fails to create a thread when asked.  If the thread
   * creation fails, either due to the thread factory returning
   * null, or due to an exception (typically OutOfMemoryError in
   * thread#start), we roll back cleanly.
   * 检查根据当前线程池的状态和给定的边界(core or maximum)是否可以创建一个新的worker
   * 如果是这样的话，worker的数量做相应的调整，如果可能的话，创建一个新的worker并启动，参数中的firstTask作为worker的第一个任务
   * 如果方法返回false，可能因为pool已经关闭或者调用过了shutdown
   * 如果线程工厂创建线程失败，也会失败，返回false
   * 如果线程创建失败，要么是因为线程工厂返回null，要么是发生了OutOfMemoryError
   *
   * @param firstTask the task the new thread should run first (or
   * null if none). Workers are created with an initial first task
   * (in method execute()) to bypass(绕开) queuing when there are fewer
   * than corePoolSize threads (in which case we always start one),
   * or when the queue is full (in which case we must bypass queue).
   * Initially idle threads are usually created via
   * prestartCoreThread or to replace other dying workers.
   *
   * @param core if true use corePoolSize as bound, else
   * maximumPoolSize. (A boolean indicator is used here rather than a
   * value to ensure reads of fresh values after checking other pool
   * state).
   * @return true if successful

  private boolean addWorker(Runnable firstTask, boolean core) {
      //外层循环，负责判断线程池状态
      retry:
      for (;;) {
          int c = ctl.get();
          int rs = runStateOf(c); //状态

          // Check if queue empty only if necessary.

           * 线程池的state越小越是运行状态，runnbale=-1，shutdown=0,stop=1,tidying=2，terminated=3
           * 1、如果线程池state已经至少是shutdown状态了
           * 2、并且以下3个条件任意一个是false
           *   rs == SHUTDOWN         （隐含：rs>=SHUTDOWN）false情况： 线程池状态已经超过shutdown，可能是stop、tidying、terminated其中一个，即线程池已经终止
           *   firstTask == null      （隐含：rs==SHUTDOWN）false情况： firstTask不为空，rs==SHUTDOWN 且 firstTask不为空，return false，场景是在线程池已经shutdown后，还要添加新的任务，拒绝
           *   ! workQueue.isEmpty()  （隐含：rs==SHUTDOWN，firstTask==null）false情况： workQueue为空，当firstTask为空时是为了创建一个没有任务的线程，再从workQueue中获取任务，如果workQueue已经为空，那么就没有添加新worker线程的必要了
           * return false，即无法addWorker()

          if (rs >= SHUTDOWN &&
                  ! (rs == SHUTDOWN &&
                          firstTask == null &&
                          ! workQueue.isEmpty()))
              return false;

          //内层循环，负责worker数量+1
          for (;;) {
              int wc = workerCountOf(c); //worker数量
              //如果worker数量>线程池最大上限CAPACITY（即使用int低29位可以容纳的最大值）
              //或者( worker数量>corePoolSize 或  worker数量>maximumPoolSize )，即已经超过了给定的边界
              if (wc >= CAPACITY ||
                      wc >= (core ? corePoolSize : maximumPoolSize))
                  return false;

              //调用unsafe CAS操作，使得worker数量+1，成功则跳出retry循环
              if (compareAndIncrementWorkerCount(c))
                  break retry;

              //CAS worker数量+1失败，再次读取ctl
              c = ctl.get();  // Re-read ctl

              //如果状态不等于之前获取的state，跳出内层循环，继续去外层循环判断
              if (runStateOf(c) != rs)
                  continue retry;
              // else CAS failed due to workerCount change; retry inner loop
              // else CAS失败时因为workerCount改变了，继续内层循环尝试CAS对worker数量+1
          }
      }

      /**
       * worker数量+1成功的后续操作
       * 添加到workers Set集合，并启动worker线程

      boolean workerStarted = false;
      boolean workerAdded = false;
      Worker w = null;
      try {
          final ReentrantLock mainLock = this.mainLock;
          w = new Worker(firstTask); //1、设置worker这个AQS锁的同步状态state=-1
          //2、将firstTask设置给worker的成员变量firstTask
          //3、使用worker自身这个runnable，调用ThreadFactory创建一个线程，并设置给worker的成员变量thread
          final thread t = w.thread;
          if (t != null) {
              mainLock.lock();
              try {
                  //--------------------------------------------这部分代码是上锁的
                  // Recheck while holding lock.
                  // Back out on ThreadFactory failure or if
                  // shut down before lock acquired.
                  // 当获取到锁后，再次检查
                  int c = ctl.get();
                  int rs = runStateOf(c);

                  //如果线程池在运行running<shutdown 或者 线程池已经shutdown，且firstTask==null（可能是workQueue中仍有未执行完成的任务，创建没有初始任务的worker线程执行）
                  //worker数量-1的操作在addWorkerFailed()
                  if (rs < SHUTDOWN ||
                          (rs == SHUTDOWN && firstTask == null)) {
                      if (t.isAlive()) // precheck that t is startable   线程已经启动，抛非法线程状态异常
                          throw new IllegalThreadStateException();
                      workers.add(w);//workers是一个HashSet<Worker>
                      //设置最大的池大小largestPoolSize，workerAdded设置为true
                      int s = workers.size();
                      if (s > largestPoolSize)
                          largestPoolSize = s;
                      workerAdded = true;
                  }
                  //--------------------------------------------
              }
              finally {
                  mainLock.unlock();
              }

              //如果往HashSet中添加worker成功，启动线程
              if (workerAdded) {
                  t.start();
                  workerStarted = true;
              }
          }
      } finally {
          //如果启动线程失败
          if (! workerStarted)
              addWorkerFailed(w);
      }
      return workerStarted;
  }
*/