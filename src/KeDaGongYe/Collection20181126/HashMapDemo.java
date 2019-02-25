package keDaGongYe.Collection20181126;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> linkedMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedMap.put("1", "a");
        linkedMap.put("2", "b");
        linkedMap.put("3", "c");
        linkedMap.put("4", "e");

        HashSet<String> set = new HashSet<>();
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> re = new HashSet<>();
        set.add("lyp");
        set.add("baijuyi");
        set.add("libai");
        set.add("cc");
        set.add("dd");
        set.add("ll");
        set.add("hehe");
        set.add("dingding");
        set.add("jing");
        set.add("olp");
        set1.add("45");
        set1.add("475");
        set1.add("ll");
        set1.add("dd");

        set.remove("lyp");
        set.removeAll(set1);
        Iterator<String> iterator1 = set.iterator();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }

//        re.addAll(set);
//        re.retainAll(set1);
//        Iterator<String> iterator = re.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }

//        System.out.println(Arrays.toString(set.toArray()));
//        Iterator<String> iterator1 = set.iterator();
//        while (iterator1.hasNext()) {
//            System.out.print(iterator1.next() + " ");
//        }

//        linkedMap.get("1");
//        //linkedMap.get("2");
//
//        for (Iterator<String> iterator = linkedMap.values().iterator(); iterator
//                .hasNext(); ) {
//            String name = (String) iterator.next();
//            System.out.print(name + " ");
//        }
//        System.out.println();

        //增删改查
        //统计一下1000 以内 奇数和偶数的个数
        //String 奇数/偶数  Integer  个数
//        for (int i = 0; i <= 1000; i++) {
//            if (i % 2 == 0) {
//                if (map.containsKey("偶数")) {
//                    map.replace("偶数", map.get("偶数") + 1);
//                } else {
//                    map.put("偶数", 1);
//                }
//            } else {
//                if (map.containsKey("奇数")) {
//                    map.replace("奇数", map.get("奇数") + 1);
//                } else {
//                    map.put("奇数", 1);
//                }
//            }
//        }
//        System.out.println("偶数：" + map.get("偶数"));
//        System.out.println("奇数：" + map.get("奇数"));
//        Iterator<String> keyIterator = map.keySet().iterator();
//        //key set  集合  get（）  foreach   map.keySet
//        Iterator<Integer> valueIterator = map.values().iterator();
//        //value   集合  foreach  map.values
//        Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        //key value     entry  foreach  map.entrySet

//        System.out.println("keySet().iterator: ");
//        Iterator<String> keyIteractor = map.keySet().iterator();
//        while (keyIteractor.hasNext()) {
//            System.out.print(keyIteractor.next() + " ");
//        }
//        System.out.println();
//        System.out.println("keySet().iterator foreach: ");
//        for (String s : map.keySet()) {
//            System.out.print(s + " ");
//        }
//        System.out.println();
//        System.out.println("values().iterator: ");
//        Iterator<Integer> valueIteractor = map.values().iterator();
//        while (valueIteractor.hasNext()) {
//            System.out.print(valueIteractor.next() + " ");
//        }
//        System.out.println();
//        System.out.println("values().iterator  foreach : ");
//        for (Integer i : map.values()) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println("entrySet().iterator: ");
//        Iterator<Map.Entry<String, Integer>> entryIterctor = map.entrySet().iterator();
//        while (entryIterctor.hasNext()) {
//            System.out.print(entryIterctor.next() + " ");
//        }
//        System.out.println();
//        System.out.println("entrySet().iterator foreach: ");
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.print(entry + " ");
//        }
    }
}

