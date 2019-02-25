package keDaGongYe.concurrentHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer2 {
    private final Map<String, String> cache = new ConcurrentHashMap<>();


    public void add(int i) {
        cache.put(String.valueOf(i),String.valueOf(i+1));
    }


}
