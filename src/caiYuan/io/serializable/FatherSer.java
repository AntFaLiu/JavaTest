package caiYuan.io.serializable;

import java.io.*;

public class FatherSer {
    public static void main(String[] args) {
        try {
            File sunFile = new File("sun.obj");
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(sunFile));
            sunFile.deleteOnExit();
            Son son = new Son();
            out.writeObject(son);
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "sun.obj"));
            Son t = (Son) oin.readObject();
            System.out.println(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }
}


class Father  {
    public int i;

    public String name;

    public Father() {
        i = 5;
        name = "liuyupeng";
    }

    @Override
    public String toString() {
        return "father [Name=" + name + ", i=" + i + "]";
    }
}

class Son extends Father implements Serializable {
    public int age;
    public transient String pass;
    Father father = new Father();

    public Son() {
        age = 30;
        pass = "12";
    }

    @Override
    public String toString() {
        return "Son [age=" + age + ", pass=" + pass + " " + father + "]";
    }
}