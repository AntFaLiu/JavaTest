package keDaGongYe.concurrentHashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IteratorMap {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        init(map);

        //方式一：在for-each循环中使用entries来遍历
        System.out.println("方式一：在for-each循环中使用entries来遍历");
        for(Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        //方法二：在for-each循环中遍历keys或values,这种方式适用于需要值或者键的情况,方法二比方法一快了10%
        System.out.println("方法二：在for-each循环中遍历keys或values,这种方式适用于需要值或者键的情况");
        //遍历键
        for(String key : map.keySet()) {
            System.out.println("key = " + key);
        }

        //遍历值
        for(String value : map.values()) {
            System.out.println("value = " + value);
        }

        //方法三：使用Iterator遍历,使用并发集合不会报异常,性能类似于方法二
        //使用泛型
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        System.out.println("使用Iterator遍历,并且使用泛型:");
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            //注意这里操作了集合,下面的的遍历不会再打印0
            if("0".equals(entry.getKey())) {
                map.remove(entry.getKey());
            }
        }

        //不使用泛型
        Iterator entrys = map.entrySet().iterator();
        System.out.println("使用Iterator遍历,并且不使用泛型");
        while (entrys.hasNext()) {
            Map.Entry entry = (Map.Entry) entrys.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            System.out.println("Key = " + key + ", Value = " + value);
        }

        //方式四：通过键找值遍历,该方法效率相当低,不建议使用
        System.out.println("方式四：通过键找值遍历");
        for (String key : map.keySet()) {

            String value = map.get(key);

            System.out.println("Key = " + key + ", Value = " + value);

        }
    }

    /**
     * 初始化Map
     * @param map
     */
    private static void init(Map<String, String> map) {
        if(map == null) {
            throw new RuntimeException("参数为空,无法执行初始化");
        }
        for(int i = 0; i < 10; i ++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
    }
}
