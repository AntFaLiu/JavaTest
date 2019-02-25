package caiYuan.io.serializaTest;

import java.io.Serializable;

class Father implements Serializable {
    private int fatherFge;
    private String fatherName;

    public Father() {
        this.fatherFge = 50;
        this.fatherName = "xxx";
    }

    public int getFatherFge() {
        return fatherFge;
    }

    public void setFatherFge(int fatherFge) {
        this.fatherFge = fatherFge;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return "Father{" +
                "fatherFge=" + fatherFge +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}

public class Person implements Serializable, Cloneable {

    private int age;
    private String name;
    private String sex;

    public Person() {
    }

    public Person(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {
        return (Person)super.clone();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}