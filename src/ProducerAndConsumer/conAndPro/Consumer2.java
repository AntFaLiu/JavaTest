package producerAndConsumer.conAndPro;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer2 extends Thread {
    private BlockingQueue<Integer> cache;
    private Integer deafultNum = 100;
    private Random random = new Random();
    public Consumer2(BlockingQueue<Integer> cache){
        this.cache = cache;
    }

    @Override
    public void run() {
        while (deafultNum-- > 0) {
            try {
                Thread.sleep(random.nextInt(2000));
                Integer value = cache.take();
                System.out.println(Thread.currentThread().getName()+" consumer:"+value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


