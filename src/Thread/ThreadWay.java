package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadWay {
    public static void main(String[] args) {
        //方式一 继承Thread类
//        Demo threadDemo01 = new Demo();
//        threadDemo01.setName("我是自定义的线程1");
//        threadDemo01.start();
//        System.out.println(Thread.currentThread().toString());
//
//        //方式二 实现runnable接口
//        Thread t1 = new Thread(new DemoOne());
//        t1.setName("我是自定义的线程2");
//        System.out.println(Thread.currentThread().getName());
//        t1.start();
//
//        //方式三 实现callable接口
//        Callable<Object> oneCallable = new Tickets<Object>();
//        FutureTask<Object> oneTask = new FutureTask<Object>(oneCallable);
//
//        Thread t = new Thread(oneTask);
//        t.setName("我是自定义的线程3");
//        System.out.println(Thread.currentThread().getName());
//        t.start();


        ThreadDemo td = new ThreadDemo();

        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> result = new FutureTask<>(td);

        new Thread(result).start();

        //2.接收线程运算后的结果
        try {
            Integer sum = result.get();  //FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

//方式一 继承Thread类
class Demo extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }
}

//方式二 实现runnable接口
class DemoOne implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println(Thread.currentThread().getName() + "-->我是通过实现接口的线程实现方式！");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }
}

/*
 * 线程实现方式3：通过Callable和FutureTask创建线程
 * a:创建Callable接口的实现类 ，并实现Call方法
 * b:创建Callable实现类的实现，使用FutureTask类包装Callable对象，该FutureTask对象封装了Callable对象的Call方法的返回值
 * c:使用FutureTask对象作为Thread对象的target创建并启动线程
 * d:调用FutureTask对象的get()来获取子线程执行结束的返回值
 */
class Tickets<Object> implements Callable<Object> {

    //重写call方法
    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
        return null;
    }
}

class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }
<<<<<<< HEAD

=======
>>>>>>> e9a8ac4bc61e3236f5d880801452327fefa30bd7
        return sum;
    }
}

// Callable 和 Future接口的区别
<<<<<<< HEAD
//
=======
>>>>>>> e9a8ac4bc61e3236f5d880801452327fefa30bd7
//（1）Callable规定的方法是call()，而Runnable规定的方法是run().
//（2）Callable的任务执行后可返回值，而Runnable的任务是不能返回值的。
//（3）call()方法可抛出异常，而run()方法是不能抛出异常的。
//（4）运行Callable任务可拿到一个Future对象， Future表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。
// 通过Future对象可了解任务执行情况，可取消任务的执行，还可获取任务执行的结果。Callable是类似于Runnable的接口，
// 实现Callable接口的类和实现Runnable的类都是可被其它线程执行的任务。
