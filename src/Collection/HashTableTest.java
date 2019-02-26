package collection;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class HashTableTest {
    public static void main(String[] args) {
        Hashtable<String, String> table = new Hashtable();
        table.put("lyp", "123");
        table.put("yoie", "123");
        table.put("rewr", "123");
        table.put("dsfd", "123");
        table.put("fdsf", "123");
        table.put("fdsf", "123");
        table.put("rew", "123");
        table.put("hf", "123");
        table.put("fds", "123");
//        table.put(null,"hfdj");
//        table.put("dfds",null);  //HashTable 不允许key,value为空,

        //1、使用keys()
        System.out.println("使用keys():");
        Enumeration<String> en1 = table.keys();
        while (en1.hasMoreElements()) {
            String key = en1.nextElement();
            System.out.print("  value:" + key + " value:" + table.get(key) + " ");
        }
        System.out.println();
//2、使用elements()
        System.out.println("使用elements():");
        Enumeration<String> en2 = table.elements();
        while (en2.hasMoreElements()) {
            String value = en2.nextElement();
            System.out.print("  value:" + value + " ");
        }

//3、使用keySet()
        System.out.println();
        System.out.println("使用keySet():");
        Iterator<String> it1 = table.keySet().iterator();
        while (it1.hasNext()) {
            String key = it1.next();
            System.out.print("  key: " + key + " value:" + table.get(key));
        }
        System.out.println();
        System.out.println("使用keySet():  foreach 形式");
        for (String e : table.keySet())
            System.out.print(" key: " + e + "  value: " + table.get(e));
        System.out.println();
//4、使用entrySet()
        System.out.println();
        System.out.println("使用entrySet():");
        Iterator<Map.Entry<String, String>> it2 = table.entrySet().iterator();
        while (it2.hasNext()) {
            System.out.print(it2.next() + " ");
        }
        System.out.println();
        System.out.println("使用entrySet():  foreach 形式");
        for (Map.Entry e : table.entrySet())
            System.out.print(" key: " + e.getKey() + "  value: " + e.getValue());
        System.out.println();
        System.out.println("table.toString():  "+table.toString());


        Iterator<String> valueIterator = table.values().iterator();
        System.out.println();
        System.out.println("使用value遍历");
        while (valueIterator.hasNext()) {
            String value = valueIterator.next();
            System.out.print(value + " ");
        }
    }


}
