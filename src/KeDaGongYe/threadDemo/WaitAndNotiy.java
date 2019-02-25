package keDaGongYe.threadDemo;

import java.util.LinkedList;
import java.util.Queue;

public class WaitAndNotiy {

    private static Queue<Integer> queue = new LinkedList<>();

    private static Byte[] bytes = new Byte[2];
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotiy waitAndNotiy = new WaitAndNotiy();
        for (int i = 0; i < 15; i++) {
            waitAndNotiy.add(i);
        }
        System.out.println(waitAndNotiy.queue.size());
    }

    public   void add(int i) throws InterruptedException {
        synchronized (bytes) {
            while (queue.size() == 10) {
                bytes.wait();
            }
            queue.add(i);
            bytes.notifyAll();
        }
    }
}

class Producer implements Runnable{
    private Queue<Integer> queue;
    private static int i;
    @Override
    public void run() {
        synchronized (queue) {
            while (queue.size() >= 10) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            queue.add(i);
            queue.notifyAll();
        }
    }
}

class Comsumer  implements Runnable{
    private Queue<Integer> queue;
    @Override
    public void run() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {  //Interrupt()
                    e.printStackTrace();
                }
            }
            queue.remove();
            queue.notifyAll();
        }
    }
}
