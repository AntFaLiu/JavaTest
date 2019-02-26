package collection.Practicequestions;

import java.util.ArrayList;
import java.util.List;

public class UnionArrayList {
    public static void union(List<Integer> a, List<Integer> b) {//并集
        a.addAll(b);
        for (Integer elem : a) {
            System.out.print(elem + " ");
        }
    }

    public static void Intersection(List<Integer> a, List<Integer> b) {//交集
        a.retainAll(b);
        for (Integer elem : a) {
            System.out.print(elem + " ");
        }
    }

    public static void differenceSet(List<Integer> a, List<Integer> b) {//差集
        a.removeAll(b);
        for (Integer elem : a) {
            System.out.print(elem + " ");
        }
    }

    public static void noRepetitionUnion(List<Integer> a, List<Integer> b) { //无重复并集
        b.removeAll(a); //先求差集再求
        a.addAll(b);
        for (int i = 0;i < a.size();i++) {
            System.out.print(a.get(i)+" ");
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        List<Integer> brr = new ArrayList<>();

        for (int i = 3; i < 100; i = i * 2) {
            arr.add(i);
        }
        for (Integer a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();

        for (int i = 3; i < 100; i = i * 3) {
            brr.add(i);
        }

        for (Integer b : brr) {
            System.out.print(b + " ");
        }

//        System.out.println();
//        System.out.println("并集: ");
//        union(arr, brr);
//        System.out.println();
//        System.out.println("交集: ");
//        Intersection(arr, brr);
//        System.out.println();
//        System.out.println("差集: ");
//        differenceSet(arr, brr);
        System.out.println();
        System.out.println("无重复并集: ");
        noRepetitionUnion(arr, brr);




    }
}
