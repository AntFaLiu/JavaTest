package KeDaGongYe.Collection20181126;

import java.util.*;

public class LinkedListTest {
    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<Integer>();
        Queue queue = new LinkedList();


        int count = 0;
        for (int i = 0; i < 100; i++) {
            list1.add(i, i);
        }

        for (int i = 0; i < 100; i++) {
            list2.add(i);
        }

        Iterator it1 = list1.iterator();
        while (it1.hasNext()) {
            count++;
            if (count == 37) {
                System.out.print(it1.next() + " ");
            }
        }
        System.out.println();
        it1 = list1.iterator();
        while (it1.hasNext()) {
            it1.next();
            count++;
            if (count == 50) {
                it1.remove();
                break;
            }
        }
        System.out.println();
        System.out.println("第37号元素是：" + list1.get(37));

        System.out.println("size()" + list1.size());
        for (Integer value : list1) {
            System.out.print(value + " ");
        }
        System.out.println();
        List<String> list3 = new LinkedList<>();
        list3.add("hjadsfh");
        list3.add("lyp");
        list3.set(0, "aaaaa");
        list3.remove("lyp");
        for (String a : list3) {
            System.out.println(a);
        }
    }
}
