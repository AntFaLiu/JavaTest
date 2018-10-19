package IODemo;

import java.io.*;
class IntSer{
    public String tmp;
}
public class IoTest1 {
    public static void main(String[] args) throws IOException {
//        File f = new File("/Users/ant_oliu/Documents/资料/党员个人信息情况表.doc");
//        RandomAccessFile raf=new RandomAccessFile(f,"rw");//读写模式，如果该路径不存在会自动创建
//        int a = raf.read();
//        System.out.println(a);
//        String name1="jim";
//        int age1 =20;
//        String name2="Tom";
//        int age2=30;
//        raf.writeBytes(name1);
//        raf.writeInt(age1);
//        raf.writeBytes(name2);
//        raf.writeInt(age2);
//        raf.close();
        String source = "/Users/ant_oliu/Documents/资料/党员个人信息情况表.doc";
        String dest = "/Users/ant_oliu/Documents/资料/lypJavaTest.txt";
        copyWithFileStream(source,dest);
        copyWithObjectStream(source,dest);
        copyWithBufferedStream(source,dest);
        copyWithDateStream(source,dest);

    }

    private static void copyWithObjectStream(String source, String dest) {
        long millis = System.currentTimeMillis();

        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dest));
            IntSer tmp;
            while ((tmp = (IntSer)inputStream.readObject()) != null) {
//                System.out.println(tmp);
                outputStream.writeObject(tmp);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long l = System.currentTimeMillis() - millis;
        System.out.println("copyWithObjectStream totalTime:"+l);
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
        System.out.println("copyWithFileStream totalTime:"+l);
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
        System.out.println("copyWithBufferedStream totalTime:"+l);
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
        System.out.println("copyWithDateStream totalTime:"+l);
    }
}
