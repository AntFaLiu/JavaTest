package caiYuan.io.serializaTest;

public class ClonePerson {
    public static void main(String[] args) {
        Person p = new Person(18, "图论科技", "男");
        Person pClone = null;
        try {
            pClone = p.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(pClone);
    }
}
