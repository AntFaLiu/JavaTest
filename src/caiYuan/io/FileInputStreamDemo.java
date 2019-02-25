package caiYuan.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FileInputStreamDemo {
    public static void main(String[] args) {
        try {
            File file = new File("E:/JavaIOTet.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(fileInputStream.available());
            fileInputStream.read();  //整形值  0 —— 255 -2^7  2^7-1


            System.out.println(file.length());
            System.out.println(fileInputStream.available());
//            FileChannel fileChannel = fileInputStream.getChannel();
//            ByteBuffer buff = ByteBuffer.allocate(1024);
//            fileChannel.read(buff);
//            System.out.println(new String(buff.array(), "GBK"));
//
//            FileOutputStream fileOutputStream = new FileOutputStream("E:/JavaIOTet.txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
