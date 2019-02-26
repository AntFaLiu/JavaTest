package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraysTest {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int b[] = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int c[] = Arrays.copyOf(a, 5);
        int d[] = Arrays.copyOfRange(b, 3, 5);   //表示下标左闭右开
        int arr[][] = {{1, 2, 3}, {4, 5, 6}};
        int brr[][] = {{1, 2, 3}, {4, 5, 6}};
        int drr[][][] = {{{1, 2, 3}, {43, 65, 76}, {3, 6, 7}, {232}, {23},}, {{1, 2, 3}, {3, 6, 7}}};
        int crr[] = {1, 2, 3};
        int err[] = {1, 2, 3};
        System.out.println("Arrays.equals(crr,err): " + Arrays.equals(crr, err));
        System.out.println("Arrays.equals(arr, brr): " + Arrays.equals(arr, brr));
        System.out.println("Arrays.deepEquals(arr,brr):  " + Arrays.deepEquals(arr, brr));
//      Arrays.deepToString()与Arrays.toString()的区别
//      Arrays.deepToString()主要用于数组中还有数组的情况，
//      而Arrays.toString()则相反，对于Arrays.toString()而言，当数组中有数组时，不会打印出数组中的内容，只会以地址的形式打印出来。
        System.out.println("" + Arrays.deepToString(drr));
        System.out.println("Arrays.deepToString(arr): " + Arrays.deepToString(arr) + "Arrays.toString(arr): " + Arrays.toString(arr));
        System.out.println("c: " + Arrays.toString(c));
        System.out.println("d: " + Arrays.toString(d));

        Student[] stus = Student.initData();
        System.out.print("源数组Student[]内容为: \n");
        for (int i = 0; i < stus.length; i++) {
            System.out.println(stus[i].toString());
        }
        System.out.println();
        //通过java.util.Arrays类的copyOf方法对源数组进行复制
        Student[] dest = Arrays.copyOf(stus, 6);

        System.out.print("复制长度为6的子数组: \n");
        for (int i = 0; i < dest.length; i++) {
            //System.out.print(dest[i] + " ");
            System.out.println(dest[i].toString());
        }
        System.out.println();
        //通过java.util.Arrays类的copyOf方法对源数组进行复制（如果复制的长度超过了源数组的长度，则用0填充）
        Student[] dest1 = Arrays.copyOf(stus, 20);
        System.out.print("复制长度为20的子数组: \n");
        for (int i = 0; i < dest1.length; i++) {
            if (dest1[i] != null) {
                System.out.println(dest1[i].toString());
            }
        }
        System.out.println();
    }
}

class Student {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

    public static Student[] initData() {
        Student[] stus = new Student[10];
        for (int i = 0; i < 10; i++) {
            Student stu = new Student();
            stu.setName("同学" + i);
            stu.setAge(i);
            stus[i] = stu;
        }
        return stus;
    }
}

class MyArrays {
    public static <T> List<T> asList(T... a) {
        List<T> list = new ArrayList<T>();
        Collections.addAll(list, a);
        return list;
    }
}

class TestDemo {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
        print(stooges);
        List<List<String>> seasonsList = Arrays.asList(retrieveSeasonsList());
        print(seasonsList);
        /*
         * 自己实现一个asList方法，能够添加和删除。
         */
        List<String> list = MyArrays.asList("Larry", "Moe", "Curly");
        list.add("Hello");
        print(list);
        int drr[][][] = {{{1, 2, 3}, {43, 65, 76}, {3, 6, 7}, {232}, {23},}, {{1, 2, 3}, {3, 6, 7}}};
        List<int[][][]> a = MyArrays.asList();
        print(a);

    }

    private static <T> void print(List<T> list) {
        System.out.println(list);
    }

    private static List<String> retrieveSeasonsList() {
        List<String> seasonsList = new ArrayList<String>();
        seasonsList.add("Spring");
        seasonsList.add("Summer");
        seasonsList.add("Autumn");
        seasonsList.add("Winter");
        return seasonsList;
    }
}
