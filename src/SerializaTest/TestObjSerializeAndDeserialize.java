package SerializaTest;

import java.io.*;
import java.text.MessageFormat;

public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        SerializePerson();//序列化Person对象
        Person p = DeserializePerson();//反序列Perons对象
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                p.getName(), p.getAge(), p.getSex()));
    }

    /**
     * MethodName: SerializePerson
     * Description: 序列化Person对象
     * @throws FileNotFoundException
     * @throws IOException
     * @author xudp
     */
    private static void  SerializePerson() throws FileNotFoundException,
            IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(23);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！" );
        oo.close();
    }

    /**
     * MethodName: DeserializePerson
     * Description: 反序列Perons对象
     *
     * @return
     * @throws Exception
     * @throws IOException
     * @author xudp
     */
    private static Person DeserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("E:/Person.txt")));
        Person p;
        p = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return p;
    }
}
