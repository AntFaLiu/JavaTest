package producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resdffss {
    int queue;
    Lock lock = new ReentrantLock();
    Condition con1 = lock.newCondition();
    Condition con2 = lock.newCondition();
}
class Input1 implements Runnable {
    Resdffss resdffss;
    Input1(Resdffss resdffss) {
        this.resdffss = resdffss;
    }
    @Override
    public void run() {
        int j = 1;
        for(int i = 0;i < 10;i++) {
            resdffss.lock.lock();
            while (resdffss.queue > 0) {
                try {
                    resdffss.con1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"生产者"+j);
            j++;
            resdffss.queue++;
            resdffss.con2.signal();
            resdffss.lock.unlock();
        }
    }
}
class Output1 implements Runnable {
    Resdffss resdffss;
    Output1(Resdffss resdffss) {
        this.resdffss = resdffss;
    }
    @Override
    public void run() {
        int k = 1;
        for(int i = 0;i < 10;i++) {
            resdffss.lock.lock();
            while (resdffss.queue == 0) {
                try {
                    resdffss.con2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"消费者"+k);
            k++;
            resdffss.queue--;
            resdffss.con1.signal();
            resdffss.lock.unlock();
        }
    }
}
public class ScXf {
    public static void main(String[] args) {
        Resdffss resdffss = new Resdffss();
        Input1 input1 = new Input1(resdffss);
        Output1 output1 = new Output1(resdffss);
        Thread thread = new Thread(input1);
        Thread thread1 = new Thread(output1);
        thread.start();
        thread1.start();
    }
}
