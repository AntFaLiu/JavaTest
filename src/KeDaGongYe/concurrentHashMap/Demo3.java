package keDaGongYe.concurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        /** 全局HashMap*/
        HashMap<Integer, Integer> hashMap = new HashMap();

        new Thread(new AddThread(hashMap, 3)).start();
        new Thread(new RemoveThread(hashMap)).start();
        new Thread(new IteratorThread(hashMap)).start();

//        thread.sleep(2000);
//        System.out.println(hashMap.size());
    }
}

class AddThread implements Runnable {
    Map<Integer, Integer> hashMap;
    private int value = 0;


    public AddThread(Map<Integer, Integer> hashMap, int i) {
        this.hashMap = hashMap;
        this.value = i;
    }

    @Override
    public void run() {
        while (true) {
            hashMap.put(value, value + 50);
        }
    }
}

class RemoveThread implements Runnable {
    Map<Integer, Integer> hashMap;

    public RemoveThread(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        while (true) {
            int random = new Random().nextInt(1000);
            hashMap.remove(random);
        }
    }
}

class IteratorThread implements Runnable {

    Map<Integer, Integer> hashMap;

    public IteratorThread(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("------------------ " + hashMap.size());
            for (Integer value : hashMap.values()) {
//            System.out.println("value " + value);
            }
            System.out.println("+++++++++++++++++++ " + hashMap.size());
        }
    }

}
