package KeDaGongYe.Collection20181126;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest {
//    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
////        set.add("kkkk");
////        set.retainAll();
////        set.removeAll();
////        set.toArray();
////        set.addAll();
//
//        //交集   差集  并集（addAll） 不重复的交集
//
//        //能否为空  重复
//        set.add(null);
//        set.add(null);
//        set.add(null);
//        set.add("oo");
//        set.add("oo");
//        set.add("dd");
//        set.add("cc");
//        set.add("uu");
//        set.add("rr");
//        Iterator<String> iterator = set.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
//    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 1; i < 5; i++) {
            set1.add(i);  //1 2 3  4
            set2.add(i + 1);//2 3 4 5
        }
//        union(set1, set2);
        getTogether(set1, set2);
//        nu(set1, set2);
    }

    private static void union(Set<Integer> set, Set<Integer> set1) {
        set.addAll(set1);
        System.out.print("并集是：");
        for (Integer i : set) {
            System.out.print(i + " ");
        }
    }

    private static void getTogether(Set<Integer> set, Set<Integer> set1) {
        set.retainAll(set1);
        System.out.print("交集是：");
        for (Integer i : set) {
            System.out.print(i + " ");
        }
    }

    private static void nu(Set<Integer> set, Set<Integer> set1) {
        set.removeAll(set1);
        System.out.print("差集是：");
        for (Integer i : set) {
            System.out.print(i + " ");
        }
    }
}
