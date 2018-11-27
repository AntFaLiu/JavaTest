package ProducerAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args)
    {
        final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(10);
        Runnable producerRunnable = new Runnable()
        {
            int i = 0;
            public void run()
            {
                while (true)
                {
                    try
                    {
                        bq.put(i + "");
                        System.out.println("我生产了一个" + i);
                        i++;
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable customerRunnable = new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        System.out.println("我消费了一个" + bq.take()+" 此时queue的size： "+bq.size());
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread producerThread = new Thread(producerRunnable);
        Thread customerThread = new Thread(customerRunnable);
        producerThread.start();
        customerThread.start();
    }
}
