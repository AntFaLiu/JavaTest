package caiYuan.io.serializable;

import java.io.*;

public class StaticTest implements Serializable {
    private static final long serialVersionUID = 1L;

    public static int staticVar = 5;  //看一下这里不加static前后的区别
    public static void main(String[] args) {
        try {
            //初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("result.obj"));
            StaticTest staticTest = new StaticTest();
            out.writeObject(staticTest);
            out.close();
            //序列化后修改为10
            staticTest.staticVar = 10;
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "result.obj"));
            StaticTest t = (StaticTest) oin.readObject();
            oin.close();
            //再读取，通过t.staticVar打印新的值
            System.out.println(t.staticVar);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
