package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class PrintElemTest {
    //三种方式遍历ArrayList
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();//创建列表
        for (int i = 0; i < 10; i++) {//向列表中添加10个元素
            list.add(i);
        }
        System.out.println("列表中的元素为: " + list);
        System.out.println("for 循环遍历: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println("Iterator遍历: ");
        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        System.out.println("foreach : ");
        for(Integer a : list){
            System.out.print(a+" ");
        }
//        System.out.println();
//        System.out.println("ListIterator遍历: ");
        ListIterator<Integer> li;//获得listItegerator对象
//        for (li = list.listIterator(); li.hasNext(); ) {
//            System.out.print(li.next() + " ");
//        }
        li = list.listIterator(10);  //如果不加这个会怎么样
        System.out.println();
        System.out.println("ListIterator反向遍历: ");
        for (; li.hasPrevious(); ) {
            System.out.print(li.previous() + " ");
        }

//        System.out.println();
//        System.out.println("foreach的遍历方式：");
//        for (Integer elem : list) {
//            System.out.print(elem + " ");
//        }
        System.out.println();
        //元素替换
        System.out.println("size: "+list.size());
        li = list.listIterator(list.size());
        while (li.hasPrevious()) {
            li.previous();
            li.set(22);
        }
        System.out.println();
        for (Integer elem : list) {
            System.out.print(elem + " ");
        }


//        ArrayList<Integer> list = new ArrayList<Integer>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
//        //列表迭代器继承的接口是Iterator
//        ListIterator<Integer> iterator = list.listIterator();
//        ListIterator<Integer> iterator2 = list.listIterator();
//
//        /**
//         * exception in thread "main" java.lang.IllegalStateException
//         at java.util.ArrayList$ListItr.set(ArrayList.java:941)
//         at cn.lonecloud.Iterator.myListIterator.main(myListIterator.java:14)
//         同样会报错，这个类里面的set和remove方法都需要和next()方法相结合使用
//         而且他的会改变的元素就是next()里面返回的元素
//         */
//        iterator.next();
//        iterator.set(22);//
//        iterator.set(23);//
//        //iterator.next();
//       // iterator.remove();
//        for (Integer elem : list) {
//            System.out.print(elem+" ");
//        }
//    }
    }
}
