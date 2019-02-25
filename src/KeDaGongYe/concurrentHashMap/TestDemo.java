package keDaGongYe.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class TestDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("x","1");
        concurrentHashMap.put("x","2");
        concurrentHashMap.put("x","3");
        concurrentHashMap.put("x","4");

        concurrentHashMap.putIfAbsent("y","5");
        concurrentHashMap.putIfAbsent("y","6");
        concurrentHashMap.putIfAbsent("y","7");
        concurrentHashMap.putIfAbsent("y","8");

        System.out.println("X:  "+concurrentHashMap.get("x"));

        System.out.println("Y:  "+concurrentHashMap.get("y"));
    }
}
