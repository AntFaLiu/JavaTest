package mySemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceManage {
    private final Semaphore semaphore ;
    private boolean resourceArray[];
    private final ReentrantLock lock;
    public ResourceManage() {
        this.resourceArray = new boolean[10];//存放厕所状态
        this.semaphore = new Semaphore(10,true);//控制10个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
        this.lock = new ReentrantLock(true);//公平模式的锁，先来的先选
        for(int i=0 ;i<10; i++){
            resourceArray[i] = true;//初始化为资源可用的情况
        }
    }
    public void useResource(int userId){
//        semaphore.acquire();
        try{
            semaphore.acquire();
            int id = getResourceId();//占到一个坑
            System.out.print("userId:"+userId+"正在使用资源，资源id:"+id+"\n");
            Thread.sleep(100);//do something，相当于于使用资源
            resourceArray[id] = true;//退出这个坑
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();//释放信号量，计数器加1
        }
    }
    private int getResourceId(){
        int id = -1;
        lock.lock();
        try {
            //lock.lock();//虽然使用了锁控制同步，但由于只是简单的一个数组遍历，效率还是很高的，所以基本不影响性能。
            for(int i=0; i<10; i++){
                if(resourceArray[i]){
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return id;
    }
}
class ResourceUser implements Runnable{
    private ResourceManage resourceManage;
    private int userId;
    public ResourceUser(ResourceManage resourceManage, int userId) {
        this.resourceManage = resourceManage;
        this.userId = userId;
    }
    public void run(){
        System.out.print("userId:"+userId+"准备使用资源...\n");
        resourceManage.useResource(userId);
        System.out.print("userId:"+userId+"使用资源完毕...\n");
    }

    public static void main(String[] args){
        ResourceManage resourceManage = new ResourceManage();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new ResourceUser(resourceManage,i));//创建多个资源使用者
            threads[i] = thread;
        }
        for(int i = 0; i < 100; i++){
            Thread thread = threads[i];
            try {
                thread.start();//启动线程
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
//可修改Semaphores的公平性，在默认的情况下信号量的进入是不公平的。如果在初始化的第二个参数设定为true时，则会选择时间等待最久的一个进入。
// 通过有上限的Semaphore可以限制进入某代码块的线程数量。设想一下，在上面的例子中，如果BoundedSemaphore 上限设为5将会发生什么？意味着允许5个线程同时访问关键区域，但是你必须保证，这个5个线程不会互相冲突。否则你的应用程序将不能正常运行。
// 必须注意，release方法应当在finally块中被执行。这样可以保在关键区域的代码抛出异常的情况下，信号也一定会被释放。