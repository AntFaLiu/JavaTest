package KeDaGongYe.threadDemo.homeWorkTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 将test4用Lock和Condition来改进
 */
class Function2 {
    Lock lock = new ReentrantLock();
    Condition con = lock.newCondition();
    private boolean flag = false;

    //子线程要实现的功能
    public void sub() {
        lock.lock();
        try {
            while (flag) {
                try {
                    con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 10; i++) {
                //for循环内定义子线程的功能,这里简单的假设为打印一句话,主线程同理
                System.out.println("sub" + i);
            }

            flag = true;
            con.signal();
        } finally {
            lock.unlock();
        }
    }

    //主线程要实现的功能
    public void main() {
        lock.lock();
        try {
            while (!flag) {
                try {
                    con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 20; i++) {
                System.out.println("main" + i);
            }
            flag = false;
            con.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class Test5 {
    public static void main(String[] args) {
        final Function2 f = new Function2();
        new Thread(
                new Runnable() {

                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            f.sub();
                        }
                    }

                }
        ).start();

        for (int i = 0; i < 10; i++) {
            f.main();
        }
    }
}
