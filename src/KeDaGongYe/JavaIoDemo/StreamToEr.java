package KeDaGongYe.JavaIoDemo;

import java.io.*;

public class StreamToEr {
    public static void writeStream() {
        // 第1步：使用File类找到一个文件
        File f = new File("e:" + File.separator + "test.txt"); // 声明File  对象
        // 第2步：通过子类实例化父类对象
        OutputStream out = null;
        // 准备好一个输出的对象
        try {
            out = new FileOutputStream(f);
            // 通过对象多态性进行实例化
            // 第3步：进行写操作
            String str = "Hello World!!!";
            // 准备一个字符串
            byte b[] = str.getBytes();
            // 字符串转byte数组
            out.write(b);
            // 将内容输出
            // 第4步：关闭输出流
            // out.close();
            // 此时没有关闭
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void WriteEr() {
        try {
            // 第1步：使用File类找到一个文件
            File f = new File("e:" + File.separator + "StreamToErTest1.txt");// 声明File 对象
            // 第2步：通过子类实例化父类对象
            Writer out = null;
            // 准备好一个输出的对象
            out = new FileWriter(f);
            // 通过对象多态性进行实例化
            // 第3步：进行写操作
            String str = "Hello World!!!";
            // 准备一个字符串
            out.write(str);
            out.flush();
            // 将内容输出             //要么close  要么  flush
            // 第4步：关闭输出流
             //out.close();
            // 此时没有关闭
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        writeStream();
//        WriteEr();
    }
}
