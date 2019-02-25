package keDaGongYe.Collection20181126;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
//        //增 删 改 查 扩容   数据结构
//        List<Integer> list1 = new ArrayList<Integer>();
//        List<Integer> list2 = new ArrayList<Integer>();
//        List<String>  list3 = new ArrayList<>();
//        list3.add("hjadsfh");
//        list3.add("lyp");
//        list3.set(0,"aaaaa");
//        list3.remove("lyp");
//        for(String a : list3){
//            System.out.println(a);
//        }
//        int count = 0;
//        for (int i = 0; i < 100; i++) {
//            int x = (int) (Math.random() * 100);
//            list1.add(i, x);
//        }
//
//        for (int i = 0; i < 100; i++) {
//            int x = (int) (Math.random() * 100);
//            list2.add(x);
//        }
//
//        Iterator it1 = list1.iterator();
//        while (it1.hasNext()) {
//            count++;
//            if (count == 37) {
//                System.out.print(it1.next() + "  ");
//            }
//        }
//        while (it1.hasNext()) {
//            it1.next();
//            count++;
//            if (count == 50) {
//                it1.remove();
//            }
//        }
//        System.out.println(list1.get(37));
//        for (Integer value : list1) {
//            System.out.print(value + " ");
//        }

//        int[] num = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//        int[] num1 = new int[20];
//        System.arraycopy(num,0,num1,0,20);
//
//        System.out.println(Arrays.toString(num1));
//        int[] num2 = Arrays.copyOf(num,num.length);
//        System.out.println(Arrays.toString(num2));
//        int[] num3 = num.clone();
//        System.out.println(Arrays.toString(num3));
//
//        int index = 10;
//        System.arraycopy(num,index+1,num,index,num.length-index-1);
//        System.out.println(Arrays.toString(num));
        List l = new ArrayList();
        System.out.println(l.size());

    }
}
