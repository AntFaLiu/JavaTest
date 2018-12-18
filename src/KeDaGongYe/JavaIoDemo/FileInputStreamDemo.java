package KeDaGongYe.JavaIoDemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FileInputStreamDemo {
    public static void main(String[] args) {
        try {
            File file = new File("E:/JavaIOTet.txt");
            FileInputStream fileInputStream = new FileInputStream("E:/JavaIOTet.txt");
            //System.out.println(fileInputStream.available());
            fileInputStream.skip(2);
            byte[] brr = new byte[10];
            fileInputStream.read(brr);
            System.out.println(new String(brr,"gbk"));
            FileChannel fileChannel = fileInputStream.getChannel();


//            ByteBuffer buff = ByteBuffer.allocate(1024);
//            fileChannel.read(buff);
//            System.out.println(new String(buff.array(), "GBK"));
//            System.out.println(Arrays.toString(buff.array()));
//            int len = fileInputStream.available();
//            len = fileInputStream.available();
//            byte[] buffer = new byte[10];
//            int read = fileInputStream.read();  //返回的是ASCII码
//            fileInputStream.read(buffer);
//            System.out.println(len);
//            System.out.println((char) read);
//            System.out.println(Arrays.toString(buffer));
//            System.out.println();
//            ls.read(buffer);
//            System.out.println(Arrays.toString(buffer));
//            for (byte b : buffer) {
//                System.out.print((char) b + " ");
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
