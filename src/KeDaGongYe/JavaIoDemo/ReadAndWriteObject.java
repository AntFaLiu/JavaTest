package KeDaGongYe.JavaIoDemo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//通过ObjectOutputStream和ObjectInputStream对对象进行序列化及反序列化
class User implements Serializable, Cloneable {
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
            new Date();
            User emp2 = (User) Class.forName("KeDaGongYe.JavaIoDemo.User").newInstance();
            emp2.setAge(10);
            emp2.setBirthday(new Date());
            emp2.setGender("男");
            emp2.setName("dsads");
            User emp4 = (User) emp2.clone();
            emp4.setName("wqe");
            System.out.println(emp2 + "  " + emp4);
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
        arrayListSerializableDemo();
    }

    /**
     * 序列化过程
     */
    public static void objectOutputStreamDemo() {
//        String names[] = {"衬衣","手套","围巾"} ;	// 商品名称
//        float prices[] = {98.3f,30.3f,50.5f} ;		// 商品价格
//        int nums[] = {3,2,1} ;	// 商品数量
//        File file = new File("tmpUser");
//        ObjectOutputStream objectOutputStream = null;;
//        try {
//            objectOutputStream = new ObjectOutputStream(new FileOutputStream("tmpUser"));
//            for(int i=0;i<names.length;i++){	// 循环输出
//                objectOutputStream.writeChars(names[i]) ;	// 写入字符串
//                objectOutputStream.writeChar('\t') ;	// 写入分隔符
//                objectOutputStream.writeFloat(prices[i]) ; // 写入价格
//                objectOutputStream.writeChar('\t') ;	// 写入分隔符
//                objectOutputStream.writeInt(nums[i]) ; // 写入数量
//                objectOutputStream.writeChar('\n') ;	// 换行
//                objectOutputStream.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        User user = new User();
        user.setName("tulun");
        user.setAge(10);
        user.setBirthday(new Date());
        user.setGender("男");
//        System.out.println(user);

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
//        File file = new File("tmpUser");
//        ObjectInputStream objectInputStream = null;
//        String name = null ;	// 接收名称
//        float price = 0.0f ;	// 接收价格
//        int num = 0 ;	// 接收数量
//        char temp[] = null ;	// 接收商品名称
//        int len = 0 ;	// 保存读取数据的个数
//        char c = 0 ;	// '\u0000'
//        try {
//            objectInputStream = new ObjectInputStream(new FileInputStream(file));
//            while(true) {
//                temp = new char[200];    // 开辟空间
//                len = 0;
//                while ((c = objectInputStream.readChar()) != '\t') {    // 接收内容
//                    temp[len] = c;
//                    len++;    // 读取长度加1
//                }
//
//                name = new String(temp, 0, len);    // 将字符数组变为String
//                price = objectInputStream.readFloat();    // 读取价格
//                objectInputStream.readChar();    // 读取\t
//                num = objectInputStream.readInt();    // 读取int
//                objectInputStream.readChar();    // 读取\n
//                System.out.printf("名称：%s；价格：%5.2f；数量：%d\n", name, price, num);
//                objectInputStream.close();
//            }
//        } catch (IOException e) {
//            //e.printStackTrace();
//        }

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
            System.out.println("序列化之后： " + newStringList);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
