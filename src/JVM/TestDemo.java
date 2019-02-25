package jvm;

class People {
    String name;
    int age;

    public String getName() {
        return name;
    }
}

public class TestDemo {
    public static void main(String[] args) {
        People people = new People();
        System.out.println("33333333333");
        System.out.println("People 的类加载器是：  " +
                people.getClass().getClassLoader());
    }
}
