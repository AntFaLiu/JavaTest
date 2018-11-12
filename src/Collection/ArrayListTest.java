package Collection;

//1、ArrayList的大小是如何自动增加的？你能分享一下你的代码吗？
//ArrayList Add方法：
//public boolean add(E e){
//        ensureCapacity(size+1); //Increment modCount!!
//        elementData[size++] = e;
//        return true;
//        }
//
////ensureCapacity方法：处理ArrayList的大小
//public void ensureCapacity(int minCapacity) {
//        modCount++;
//        int oldCapacity = elementData.length;
//        if (minCapacity > oldCapacity) {
//        Object oldData[] = elementData;
//        int newCapacity = (oldCapacity * 3)/2 + 1;
//        if (newCapacity < minCapacity)
//        newCapacity = minCapacity;
//        // minCapacity is usually close to size, so this is a win:
//        elementData = Arrays.copyOf(elementData, newCapacity);
//        }
//        }


//2、什么情况下你会使用ArrayList？什么时候你会选择LinkedList？
//  这又是一个大多数面试者都会困惑的问题。多数情况下，当你遇到访问元素比插入或者是删除元素更加频繁的时候，你应该使用ArrayList。
//  另外一方面，当你在某个特别的索引中，插入或者是删除元素更加频繁，或者你压根就不需要访问元素的时候，你会选择LinkedList。这里的主要原因是，
//  在ArrayList中访问元素的最糟糕的时间复杂度是”1″，而在LinkedList中可能就是”n”了。在ArrayList中增加或者删除某个元素，通常会调用System.arraycopy方法，
//  这是一种极为消耗资源的操作，因此，在频繁的插入或者是删除元素的情况下，LinkedList的性能会更加好一点。

//3.当传递ArrayList到某个方法中，或者某个方法返回ArrayList，什么时候要考虑安全隐患？如何修复安全违规这个问题呢？
//        当array被当做参数传递到某个方法中，如果array在没有被复制的情况下直接被分配给了成员变量，那么就可能发生这种情况，即当原始的数组被调用的方法改变的时候，传递到这个方法中的数组也会改变。下面的这段代码展示的就是安全违规以及如何修复这个问题。
//
//        ArrayList被直接赋给成员变量——安全隐患：
//public void setMyArray(String[] myArray){
//        this.myArray = myArray;
//        }
//public void setMyArray(String[] newMyArray){
//        if(newMyArray == null){
//        this.myArray = myArray;
//        }else{
//        this.myArray = Arrays.copyOf(newMyArray,newMyArray);
//        }
//
//        }

import LockTest.ReLock.ReentrantLocakTest;

import java.util.*;

//4、如何复制某个ArrayList到另一个ArrayList中去？写出你的代码？
//        下面就是把某个ArrayList复制到另一个ArrayList中去的几种技术：
//        使用clone()方法，比如ArrayList newArray = oldArray.clone();
//        使用ArrayList构造方法，比如：ArrayList myObject = new ArrayList(myTempObject);
//        使用Collection的copy方法。
//        注意1和2是浅拷贝(shallow copy)。
//        5、在索引中ArrayList的增加或者删除某个对象的运行过程？效率很低吗？解释一下为什么？
//        在ArrayList中增加或者是删除元素，要调用System.arraycopy这种效率很低的操作，如果遇到了需要频繁插入或者是删除的时候，你可以选择其他的Java集合，比如LinkedList。看一下下面的代码：
//        在ArrayList的某个索引i处添加元素：

class sort implements Comparable {

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class sortTwo implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

class sortThree implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

public class ArrayListTest {

    static List l = new ArrayList<>();

    public static void main(String[] args) {
        sortTwo two = new sortTwo();
//        l.add(10);
//        l.add(9);
//        l.add(11);
//        l.add(7);
//
//        l.add(8);
//        l.add(1);
//        l.add(100);
//        l.add(-1);
//        l.add(10000);
//
//        l.sort(two);
//        System.out.println(l);
        sortThree three = new sortThree();
        l.add("lya");
        l.add("lya");
        l.add("aya");
        l.add("zya");
        l.add("lya");
        l.add("lya");
        l.add("cya");
        l.add("llla");
        l.add("lyp");
        l.add("lyo");
        l.add("lyd");
        l.add("lsa");
        l.add("laa");
        l.add("lpa");
        l.add("lra");

        l.sort(three);
        System.out.println(l);


    }
}

class Program {
    public static void main(String[] args) throws Exception {
        MyArrayList<String> myArrayList = new MyArrayList<String>() {{
            add("test1");
            add("test2");
        }};

        System.out.println(myArrayList.toString());// print This override toString method.
    }
}

class MyArrayList<E> extends ArrayList<E> {

    public MyArrayList() {
    }

    public MyArrayList(List<E> l) {
        for (int i = 0; i < l.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            this.add(l.get(i));
        }
    }

    @Override
    public String toString() {
        String str = "{";
        for (E item : this) {
            str += item + ",";
        }
        str += "}";
        return str;
    }
}


//java Arraylist 和 Array 的相互转换
class Test {
    public static void main(String[] args) {
        
        int drr[][][] = {{{1, 2, 3}, {43, 65, 76}, {3, 6, 7}, {232}, {23},}, {{1, 2, 3}, {3, 6, 7}}};
        System.out.println("Arrays.asList(drr): " + Arrays.asList(drr));
        int b[] = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        List l = Arrays.asList(b);
        for (int i = 0; i < l.size(); i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            System.out.println(l.get(i));
        }
        System.out.println("Arrays.asList(b): " + new MyArrayList(l).toString());
        // System.out.println("Arrays.asList(b): " + new MyArrayListTest().toString());
        List<String> list = new ArrayList<>();
        //增加
        list.add("刘宇鹏");
        list.add("sadas");
        list.add("sasdds");
        list.add("sasdds");
        list.add("sasads");
        list.add("liuliuliuliu");
        list.add("sasdads");
        list.add("sasdas");
        list.add("sasdads");
        list.add("sasdas");
        list.add("sasdads");

        list.add("刘玉萍");

        //删除
        list.remove("刘玉萍");
        list.remove(0);

        //改
        list.set(5,"yuyuyuyuyuyuyu");

        //查对应遍历方法二




        System.out.println("list.toArray():" + Arrays.toString(list.toArray()));
        List<String> listiteator = new ArrayList<>();
        listiteator.add("aaa");
        listiteator.add("bbb");
        listiteator.add("ccc");
        //listiteator.add(1122);  就错了

        //方法一：
        for (String attribute : listiteator) {
            System.out.println(attribute);
        }
//        方法二：
//        对于ArrayList来说速度比较快, 用for循环, 以size为条件遍历:   查
        for (int i = 0; i < listiteator.size(); i++) {
            System.out.println(listiteator.get(i));
        }
//        方法三：
//        集合类的通用遍历方式, 从很早的版本就有, 用迭代器迭代
        Iterator it = listiteator.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
class ArrayListToArray{

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        Integer[] a = new Integer[3];
        a =  list.toArray(a);
        System.out.println(Arrays.toString(a));
        List l = list.subList(2,5);
        System.out.println("list.subList: \n"+l.toString());
    }
}