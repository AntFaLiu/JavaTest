package keDaGongYe.JavaIoDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Java {
    public static void main(String[] args) {
        try {
            File file = new File("E:/JavaIOTet.txt");
            FileInputStream fileInputStream = new FileInputStream("E:/JavaIOTet.txt");
            //System.out.println(fileInputStream.available());
            fileInputStream.skip(2);
            byte[] brr = new byte[2];
            fileInputStream.read(brr);
            System.out.println(new String(brr,"GBK"));
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
