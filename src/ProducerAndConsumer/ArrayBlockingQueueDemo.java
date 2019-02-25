package producerAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
    private final static ArrayBlockingQueue<Apple> queue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        new Thread(new ArrayProducer(queue)).start();
        new Thread(new ArrayProducer(queue)).start();
        new Thread(new ArrayConsumer(queue)).start();
        new Thread(new ArrayConsumer(queue)).start();
    }
}

class Apple {
    public Apple() {
    }
}


class ArrayProducer implements Runnable {
    private final ArrayBlockingQueue<Apple> mAbq;

    ArrayProducer(ArrayBlockingQueue<Apple> arrayBlockingQueue) {
        this.mAbq = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            Produce();
        }
    }

    private void Produce() {
        try {
            Apple apple = new Apple();
            mAbq.put(apple);
            System.out.println("生产:" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ArrayConsumer implements Runnable {

    private ArrayBlockingQueue<Apple> mAbq;

    ArrayConsumer(ArrayBlockingQueue<Apple> arrayBlockingQueue) {
        this.mAbq = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                comsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void comsume() throws InterruptedException {
        Apple apple = mAbq.take();
        System.out.println("消费Apple=" + apple);
    }
}
