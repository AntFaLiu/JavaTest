package keDaGongYe.threadDemo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test4 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        List<String> list = Collections.synchronizedList(new LinkedList<String>());

        for (int i = 0; i < 50; i++) {
            service.execute(() -> {
                synchronized (list) {
                    while (list.size() == 10) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("生产者当前运行的线程是：  " + Thread.currentThread());
                    list.add(Thread.currentThread().getName());
                    System.out.println("添加新元素之后list.size():  " + list.size());
                    list.notifyAll();
                }
            });
        }
        for (int i = 0; i < 50; i++) {
            service.execute(() -> {
                synchronized (list) {
                    while (list.size() == 0) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("消费者当前运行的线程是：  " + Thread.currentThread());
                    System.out.println(list.remove(0));
                    System.out.println("消费之后还剩: "+list.size());
                    list.notifyAll();
                }
            });
        }
        System.out.println("主函数当前运行的线程是：  " + Thread.currentThread() + "   service：" + service);
        service.shutdown();
    }
}
