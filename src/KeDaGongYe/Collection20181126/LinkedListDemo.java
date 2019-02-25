package keDaGongYe.Collection20181126;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
//        List<String> list  = new LinkedList<>();
        //增加
//        list.add(object);
//        list.add(index,object);
        //delete
        //set
//        list.set()
        //get
//        list.g
//       Iterator/ListIterator
//        list.contains

//        LinkedList<Integer> list = new LinkedList<>();
//        LinkedList<Integer> list1 = new LinkedList<>();
//        Queue<String> queue = new LinkedList<>();
//        //增删改查
//        //添加100个随机数
//        for(int i = 0;i < 100;i++) {
//            Random random = new Random();
//            int next = random.nextInt(10);
//            list.add(next);
//        }
//
//        for(int i = 0;i < 100;i++) {
//            Random random = new Random();
//            int next = random.nextInt(10);
//            list1.add(next);
//        }
//
//        Iterator<Integer> iterator = list.iterator();
//        while(iterator.hasNext()) {
//            Integer next = iterator.next();
//            System.out.print(next+" ");
//        }
//        System.out.println();
//
//        for(Integer item : list1) {
//            System.out.print(item+" ");
//        }
//        System.out.println();
//
//        list.remove(37);
//
//        Iterator<Integer> iterator1 = list1.iterator();
//        while(iterator1.hasNext()) {
//            int i = 0;
//            i++;
//            /*Integer next = iterator1.next();
//            System.out.println(next);*/
//            iterator1.next();
//            if(i == 37) {
//                iterator1.remove();
//            }
//
//        }
//
//        System.out.println(list.get(50));
//        System.out.println(list1.get(50));
//
//        boolean b = list.contains(5);
//        System.out.println(b);

        List<Integer> arr = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            arr.add(i); //grow
        }
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("arrayList: " + (l2 - l1) + "   LinkedList: " + (l3 - l2));

    }
}
