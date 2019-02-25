package keDaGongYe.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {
    public static void main(String[] args) {

        // 定义3个Callable类型的任务
        MyCallableTest task1 = new MyCallableTest(1);
        MyCallableTest task2 = new MyCallableTest(2);
        MyCallableTest task3 = new MyCallableTest(3);

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
            future2.cancel(true);

            // 获取第三个任务的输出，因为执行第三个任务会引起异常
            // 所以下面的语句将引起异常的抛出
            Future future3 = es.submit(task3);
            System.out.println("task3: " + future3.get());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        // 停止任务执行服务  看看区别
//        es.shutdownNow();
        es.shutdown();

    }

    public static class MyCallableTest implements Callable {
        private int flag = 0;

        public MyCallableTest(int flag) {
            this.flag = flag;
        }

        public String call() {
            if (this.flag == 1) {
                return "flag = 1";
            }
            if (this.flag == 2) {
                try {
                    while (true) {
                        System.out.println("*******flag == 2*********");
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                return "false";
            } else {
                System.out.println("*******flag == 3*********");
            }
            return null;
        }
    }
}

