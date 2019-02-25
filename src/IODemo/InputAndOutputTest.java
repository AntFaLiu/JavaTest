package ioDemo;

import java.io.*;

public class InputAndOutputTest {
    private static final String FileName = "FileTest1.txt";
    private static final String CharsetName = "copyWithFileStream.txt";
    public static void main(String[] args) {

        String source = "D:/绿里奇迹.mp4";
        String dest = "D:/1.mp4";
        String dest2 = "D:/2.mp4";
        String dest3 = "D:/3.mp4";
        String dest4 = "D:/4.mp4";
        copyWithFileStream(source, dest);
        copyWithObjectStream(source, dest2);
        copyWithBufferedStream(source, dest3);
        copyWithDateStream(source, dest4);
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

    private static void copyWithObjectStream(String source, String dest) {
        long millis = System.currentTimeMillis();

        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dest));
            IntSer tmp;
            while ((tmp = (IntSer)inputStream.readObject()) != null) {
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
