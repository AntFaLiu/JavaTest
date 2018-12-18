package KeDaGongYe.Collection20181126;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedhashMapTest {
    public static void main(String[] args) {
//        Map<String, String> map = new LinkedHashMap<>();
//        map.keySet().iterator();
//        map.values().iterator();
//        map.entrySet().iterator();
//        map.put();
//        map.remove();
//        map.size();
//        map.get();
//        map.containsValue();
//        map.containsKey();
//        map.replace();
        Hashtable<String,String> table = new Hashtable<>();

        //维护一个访问顺序
        Map<Integer, Integer> map1 = new LinkedHashMap<Integer, Integer>(16, (float) 0.75);
        for (int i = 0; i < 10; i++) {
            map1.put(i, i + 1);
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.print(" key: " + next.getKey() + " value: " + next.getValue());
        }
        map1.get(0);
        System.out.println();
        iterator = map1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.print(" key: " + next.getKey() + " value: " + next.getValue());
        }


    }
}
