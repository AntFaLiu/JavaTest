package Cloneable;

import java.io.*;

/* 通过序列化实现深拷贝   如果某个属性被transient修饰，那么该属性就无法被拷贝了。*/
public class DeepCopyBySerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DeepCopyBySerializationAge a = new DeepCopyBySerializationAge(20);
        DeepCopyBySerializationStudent stu1 = new DeepCopyBySerializationStudent("刘宇鹏", a, 175);
        //通过序列化方法实现深拷贝
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(stu1);
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        DeepCopyBySerializationStudent stu2 = (DeepCopyBySerializationStudent) ois.readObject();
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
        System.out.println();
        //尝试修改stu1中的各属性，观察stu2的属性有没有变化
        stu1.setName("lyp");
        //改变age这个引用类型的成员变量的值
        a.setAge(99);
        stu1.setLength(216);
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
    }
}

/*
 * 创建年龄类
 */
class DeepCopyBySerializationAge implements Serializable {
    //年龄类的成员变量（属性）
    private int age;

    //构造方法
    public DeepCopyBySerializationAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return this.age + "";
    }
}

/*
 * 创建学生类
 */
class DeepCopyBySerializationStudent implements Serializable {
    //学生类的成员变量（属性）,其中一个属性为类的对象
    private String name;
    private DeepCopyBySerializationAge aage;
    private int length;

    //构造方法,其中一个参数为另一个类的对象
    public DeepCopyBySerializationStudent(String name, DeepCopyBySerializationAge a, int length) {
        this.name = name;
        this.aage = a;
        this.length = length;
    }

    //eclipe中alt+shift+s自动添加所有的set和get方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeepCopyBySerializationAge getaAge() {
        return this.aage;
    }

    public void setaAge(DeepCopyBySerializationAge age) {
        this.aage = age;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    //设置输出的字符串形式
    public String toString() {
        return "姓名是： " + this.getName() + "， 年龄为： " + this.getaAge().toString() + ", 长度是： " + this.getLength();
    }
}
