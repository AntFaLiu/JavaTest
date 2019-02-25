package keDaGongYe.concurrentHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Demo4 {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(new AddThread(hashMap, i)).start();
        }
        //new thread(new RemoveThread(hashMap)).start();
//        new thread(new IteratorThread(hashMap)).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(hashMap.size());
    }

}
