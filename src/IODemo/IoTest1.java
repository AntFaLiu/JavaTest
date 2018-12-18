package IODemo;

import java.io.*;

class IntSer {
    public String tmp;
}
//ObjectInputStream与ObjectOutputStream使用以及与DataInputStream,DataOutput区别
//        结论
//        1.Object相当于装IO流的一个盒子,我们可以把对象比作一个个拼好的积木,IO流就是拼积木的积木块,那么如果要搬走积木(对象),
// 肯定需要把积木(对象)先拆了,再扔进盒子(Object)里,这就是为什么对象要序列化(Serializable)
//        2.当然装的时候我们可以有两种装法一种是全放入(output.writeObject(this))第一种盒子(ObjectInputStream)另一种是分类别
// (如:,比如屋顶,地板,这就是流里面的) 放入(output.writeUTF(number),output.writeUTF(name),output.writeInt(age)…)
// 第二种盒子(DataInputStream),所以在搬到另一个地方的时候,第一种盒子里我们把混在一起的积木块倒出((Member)intput.readObject()),
// 第二种盒子则是分块拿出来({input.readUTF(),input.readUTF(),input.readInt()…)

//IO读取时间比较
public class IoTest1 {
    public static void main(String[] args) throws IOException {
        File f = new File("E:/JavaIOTestTwo.txt");
        RandomAccessFile raf = new RandomAccessFile(f, "rw");//读写模式，如果该路径不存在会自动创建
        int a = raf.read();
        System.out.println(a);
        String name1 = "jim";
        int age1 = 20;
        String name2 = "Tom";
        int age2 = 30;
        raf.writeBytes(name1);
        raf.write(age1);
        raf.writeBytes(name2);
        raf.writeInt(age2);
        raf.close();
        String source = "E:/JavaIOTest.txt";
        String dest = "E:/JavaIOTestTwo.txt";
        copyWithFileStream(source, dest);
        copyWithObjectStream(source, dest);
        copyWithBufferedStream(source, dest);
        copyWithDateStream(source, dest);

    }

    private static void copyWithObjectStream(String source, String dest) {
        long millis = System.currentTimeMillis();

        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dest));
            IntSer tmp;
            while ((tmp = (IntSer) inputStream.readObject()) != null) {
//                System.out.println(tmp);
                outputStream.writeObject(tmp);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long l = System.currentTimeMillis() - millis;
        System.out.println("copyWithObjectStream totalTime:" + l);
    }

    private static void copyWithFileStream(String source, String dest) {
        long millis = System.currentTimeMillis();
        try {
            FileInputStream inputStream = new FileInputStream(source);
            FileOutputStream outputStream = new FileOutputStream(dest);
            int tmp;
            while ((tmp = inputStream.read()) != -1) {
//                System.out.println(tmp);
                outputStream.write(tmp);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long l = System.currentTimeMillis() - millis;
        System.out.println("copyWithFileStream totalTime:" + l);
    }

    private static void copyWithBufferedStream(String source, String dest) {
        long millis = System.currentTimeMillis();

        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest));
            int tmp;
            while ((tmp = inputStream.read()) != -1) {
                outputStream.write(tmp);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long l = System.currentTimeMillis() - millis;
        System.out.println("copyWithBufferedStream totalTime:" + l);
    }

    private static void copyWithDateStream(String source, String dest) {
        long millis = System.currentTimeMillis();

        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            DataInputStream inputStream = new DataInputStream(fileInputStream);
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(dest));
            int tmp;
            while ((tmp = inputStream.read()) != -1) {
                outputStream.write(tmp);
            }

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long l = System.currentTimeMillis() - millis;
        System.out.println("copyWithDateStream totalTime:" + l);
    }
}
