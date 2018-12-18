package Collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

public class WeakHashMapIteratorTest {

    public static void main(String[] args) {
        int val = 0;
        String key = null;
        Integer value = null;
        Random r = new Random();
        WeakHashMap map = new WeakHashMap();

        for (int i = 0; i < 12; i++) {
            // 随机获取一个[0,100)之间的数字
            val = r.nextInt(100);
            key = String.valueOf(val);
            value = r.nextInt(5);
            // 添加到WeakHashMap中
            map.put(key, value);
            System.out.println(" key:" + key + " value:" + value);
        }
        // 通过entrySet()遍历WeakHashMap的key-value
        iteratorHashMapByEntryset(map);

        // 通过keySet()遍历WeakHashMap的key-value
        iteratorHashMapByKeyset(map);

        // 单单遍历WeakHashMap的value
        iteratorHashMapJustValues(map);
    }

    /*
     * 通过entry set遍历WeakHashMap
     * 效率高!
     */
    private static void iteratorHashMapByEntryset(WeakHashMap map) {
        if (map == null)
            return;

        System.out.println("\niterator WeakHashMap By entryset");
        String key = null;
        Integer integ = null;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();

            key = (String) entry.getKey();
            integ = (Integer) entry.getValue();
            System.out.println(key + " -- " + integ.intValue());
        }
    }

    /*
     * 通过keyset来遍历WeakHashMap
     * 效率低!
     */
    private static void iteratorHashMapByKeyset(WeakHashMap map) {
        if (map == null)
            return;

        System.out.println("\niterator WeakHashMap By keyset");
        String key = null;
        Integer integ = null;
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            integ = (Integer) map.get(key);
            System.out.println(key + " -- " + integ.intValue());
        }
    }


    /*
     * 遍历WeakHashMap的values
     */
    private static void iteratorHashMapJustValues(WeakHashMap map) {
        if (map == null)
            return;
        Iterator iter =  map.values().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
