package Collection;

import java.util.*;

class Person implements Comparable {

    private int age;

    private int sex;

    private int money;

    public Person(int age, int sex, int money) {
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public int compareTo(Object o) {
        return this.money - ((Person) o).money;
    }
}

class Child {
    int age;

    int sex;

    int weight;

    public Child(int age, int sex, int weight) {
        this.age = age;
        this.sex = sex;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class ChildComparator implements Comparator<Child> {

    @Override
    public int compare(Child o1, Child o2) {
        return o1.age - o2.age;
    }
}

public class TreeMapTest {
    private final static ChildComparator childComparator = new ChildComparator();

    public static void main(String[] args) {
        Map<Person, String> tree = new TreeMap();
        Map<Child, String> tree2 = new TreeMap(childComparator);
        tree.values().iterator();
        tree.keySet().iterator();

//        Person p = new Person(32, 0, 295);
//        Person p1 = new Person(22, 0, 955);
//        Person p2 = new Person(42, 0, 935);
//        Person p3 = new Person(52, 0, 157);
//        Person p4 = new Person(33, 0, 5645);
//        Person p5 = new Person(21, 0, 9378);
//        Person p6 = new Person(15, 0, 27);
//        tree.put(p, "0");
//        tree.put(p1, "1");
//        tree.put(p2, "2");
//        tree.put(p3, "3");
//        tree.put(p4, "4");
//        tree.put(p5, "5");
//        tree.put(p6, "6");
//        Iterator<Map.Entry<Person, String>> treeIterator = tree.entrySet().iterator();
//        while (treeIterator.hasNext()) {
//            Map.Entry<Person, String> next = treeIterator.next();
//            System.out.println("key: " + next.getKey().getMoney());
//        }
//        System.out.println();

        Child c = new Child(32, 0, 295);
        Child c1 = new Child(22, 0, 955);
        Child c2 = new Child(42, 0, 935);
        Child c3 = new Child(52, 0, 157);
        Child c4 = new Child(3, 0, 5645);
        Child c5 = new Child(108, 0, 9378);
        Child c6 = new Child(96, 0, 27);
        tree2.put(c, "0");
        tree2.put(c1, "1");
        tree2.put(c2, "2");
        tree2.put(c3, "3");
        tree2.put(c4, "4");
        tree2.put(c5, "5");
        tree2.put(c6, "6");
        Iterator<Map.Entry<Child, String>> childIterator = tree2.entrySet().iterator();
        while (childIterator.hasNext()) {
            Map.Entry<Child, String> next = childIterator.next();
            System.out.println("key: " + next.getKey().age);
        }
        System.out.println("sub: ");
        SortedMap<Child, String> childStringSortedMap = ((TreeMap<Child, String>) tree2).subMap(c1, c5);
        Iterator<Child> iterator = childStringSortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().age + " ");
        }



    }
}

