package IODemo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//通过ObjectOutputStream和ObjectInputStream对对象进行序列化及反序列化
class User implements Serializable,Cloneable{
    private String name;
    private int age;
    private Date birthday;
    private String gender;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[" + name + "---" + age + "---" + birthday + "---" + gender + "]";
    }
}

public class ReadAndWriteObject {

    public static void main(String[] args) {
        try {
            User emp2 = (User) Class.forName("IODemo.User").newInstance();
            emp2.setAge(10);
            emp2.setBirthday(new Date());
            emp2.setGender("男");
            emp2.setName("dsads");
            User emp4 = (User) emp2.clone();
            emp4.setName("wqe");
            System.out.println(emp2+"  "+emp4);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
//        User emp2 = User.class.newInstance();
//        objectOutputStreamDemo();
//        objectInputStreamDemo();
//        arrayListSerializableDemo();
    }

    /**
     * 序列化过程
     */
    public static void objectOutputStreamDemo() {
        User user = new User();
        user.setName("tulun");
        user.setAge(10);
        user.setBirthday(new Date());
        user.setGender("男");
        System.out.println(user);

        ObjectOutputStream objectOutputStream = null;
        try {
            //创建对象输出流ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("tmpUser"));
            //写入数据或者对象到对象输出流
            objectOutputStream.writeObject(user);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                //关闭流（文件）
                objectOutputStream.close();
            } catch (Exception e2) {
            }
        }
    }

    /**
     * 反序列化过程
     */
    private static void objectInputStreamDemo() {
        File file = new File("tmpUser");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            User user = (User) objectInputStream.readObject();
            System.out.println(user);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                objectInputStream.close();
            } catch (Exception e2) {
            }
        }
    }

    private static void arrayListSerializableDemo() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hello");
        stringList.add("图论");
        System.out.println(stringList);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
            objectOutputStream.writeObject(stringList);
            objectOutputStream.close();
            File file = new File("stringlist");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            List<String> newStringList = (List<String>) objectInputStream.readObject();
            objectInputStream.close();
            if (file.exists()) {
                file.delete();
            }
            System.out.println(newStringList);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
