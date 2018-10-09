# 原子性

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