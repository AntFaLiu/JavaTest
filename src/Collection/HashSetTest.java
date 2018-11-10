package Collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

//HashSet、LinkedHashSet、TreeSet
//以HashSet为例：
//  HashSet中的集合元素实际底层是由HashMap的key来保存，值存放的是PERSENT。静态的Object对象。
//  元素不允许重复，对于自定义数据类型如果没有重写hashcode和equals方法，那么插入的对象默认比较的是地址。
//HashSet，linkedHashSet,Treeset三者使用的场景
// 当题目仅仅在要求插入的元素不能重复的前提下，我们使用HashSet进行元素的存储。
// 当题目在1的前提下，再要求元素的输出是按照插入的顺序进行输出操作时，我们选择使用LinkedHashSet，
// 因为LinkedHashSet底层采用双向链表来维护插入顺序。
// 当在1的前提下，要求元素按照某种排序规则来进行元素的输出操作，我们选择TreeSet（自定义数据类型实现自定义的排序方式）。
class People implements Comparable {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        People o = (People) obj;
        return this.age == o.age;
    }

    @Override
    public int hashCode() {
        return age;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + " " + age;
    }

    @Override
    public int compareTo(Object o) {
        People o1 = (People) o;
        return this.age - o1.age;
    }
}

public class HashSetTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HashSet<People> hashset = new HashSet<People>();
        People p1 = new People("zs", 10);
        People p2 = new People("lisi", 20);
        People p3 = new People("zs", 20);
        hashset.add(p1);
        hashset.add(p2);
        hashset.add(p3);

        Iterator<People> it = hashset.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("**************************LinkedHashSet***********************");
        LinkedHashSet<People> linkedHashSet = new LinkedHashSet<>();
        People p4 = new People("zs", 10);
        People p5 = new People("lisi", 20);
        People p6 = new People("zs", 20);
        linkedHashSet.add(p4);
        linkedHashSet.add(p5);
        linkedHashSet.add(p6);
        Iterator<People> peopleIterator = linkedHashSet.iterator();
        while (peopleIterator.hasNext()) {
            System.out.println(peopleIterator.next());
        }
        System.out.println("**************************TreeSet***********************");
        TreeSet<People> treeSet = new TreeSet<>();
        People p7 = new People("zs", 10);
        People p8 = new People("lisi", 20);
        People p9 = new People("zs", 20);
        Iterator<People> treeIterator = linkedHashSet.iterator();
        while (treeIterator.hasNext()) {
            System.out.println(treeIterator.next());
        }
    }
}
