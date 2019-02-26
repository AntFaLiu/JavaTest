package collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "e");

        //new add
        map.get("1");
        map.get("2");

        for (Iterator<String> iterator = map.values().iterator(); iterator
                .hasNext(); ) {
            String name = (String) iterator.next();
            System.out.print(name);
        }
    }
}
