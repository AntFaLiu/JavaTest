package caiYuan.io.serializaTest;

import java.io.*;
import java.text.MessageFormat;
import java.util.Arrays;

public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        SerializePerson();//序列化Person对象
        Person p = DeserializePerson();//反序列Perons对象
        System.out.println(p);
    }

    private static void read() {
        try {
            FileInputStream inputStream = new FileInputStream("E:/Person.txt");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            System.out.println(Arrays.toString(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MethodName: SerializePerson
     * Description: 序列化Person对象
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @author xudp
     */
    private static void SerializePerson() {

        Person person = new Person(23, "gacl", "男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(new FileOutputStream(
                    "E:/Person.txt"));
            oo.writeObject(person);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        System.out.println("Person对象序列化成功！");
    }

    private static Person DeserializePerson() {
        ObjectInputStream ois = null;
        Person p = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(
                    new File("E:/Person.txt")));
            p = (Person) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println("Person对象反序列化成功！");
        return p;
    }

    private static void DataOutPut() throws IOException {
        Person person = new Person();
        person.setName("xiaohong");
        person.setAge(26);
        person.setSex("男");
        DataOutputStream oo = null;
        try {
            // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
            oo = new DataOutputStream(new FileOutputStream(
                    new File("E:/PersonData.txt")));
            oo.writeUTF(person.getName());
            oo.writeInt(person.getAge());
            oo.writeUTF(person.getSex());
            System.out.println("Person对象分解序列化成功！");
        } finally {
            oo.close();
        }

    }

    private static Person DataInPut() {
        Person person = new Person();
        DataInputStream ois = null;
        try {
            ois = new DataInputStream(new FileInputStream(
                    new File("E:/PersonData.txt")));
            person.setName(ois.readUTF());
            person.setAge(ois.readInt());
            person.setSex(ois.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return person;
    }
}
