package keDaGongYe.Collection20181126;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Man implements Comparable {

    private int age;

    private String name;

    public Man(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return this.getAge() - ((Man) o).getAge();
    }
}


class WoMan {
    private int weight;
    private String name;

    public WoMan(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class WoManCompare implements Comparator<WoMan> {
    @Override
    public int compare(WoMan o1, WoMan o2) {
        return o1.getWeight() - o2.getWeight();
    }
}

public class ComparTest {
    public static void main(String[] args) {
        WoManCompare compare = new WoManCompare();
        Map<Man, String> man = new TreeMap<Man, String>();
        Map<WoMan, String> woMan  = new TreeMap<>(compare);
        man.put(new Man(1,"2"),null);
        man.put(new Man(4,"g"),null);
        man.put(new Man(3,"b"),null);
        man.put(new Man(5,"d"),null);
        man.put(new Man(9,"n"),null);
        man.put(new Man(2,"rt"),null);
        man.put(new Man(7,"d"),null);
        woMan.put(new WoMan(1,"2"),null);
        woMan.put(new WoMan(4,"g"),null);
        woMan.put(new WoMan(3,"b"),null);
        woMan.put(new WoMan(5,"d"),null);
        woMan.put(new WoMan(9,"n"),null);
        woMan.put(new WoMan(2,"rt"),null);
        woMan.put(new WoMan(7,"d"),null);
        System.out.println("内");
        Iterator<Man> iterator = man.keySet().iterator();
        while(iterator.hasNext()){
            Man man1 = iterator.next();
            System.out.println(man1.getAge()+" "+man1.getName());
        }
        System.out.println("外");
        Iterator<WoMan> iterator1 = woMan.keySet().iterator();
        //((TreeMap<WoMan, String>) woMan).subMap(from,boolean,to,boolean );
        while(iterator1.hasNext()){
            WoMan man1 = iterator1.next();
            System.out.println(man1.getWeight()+" "+man1.getName());
        }
    }
}
