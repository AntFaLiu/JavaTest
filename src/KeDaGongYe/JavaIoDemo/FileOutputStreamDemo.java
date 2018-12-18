package KeDaGongYe.JavaIoDemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        try {
            String str = "图论科技";
            byte[] buffer = str.getBytes("gbk");
            FileOutputStream fileOutputStream = new FileOutputStream("E:/JavaIOTest.txt");
//            fileOutputStream.write(buffer);
            fileOutputStream.write(97);  //写入a

//            String s = "sadsadeeeee";
////            fileOutputStream.write(s.getBytes());
//            fileOutputStream.write(s.getBytes(), 5, s.getBytes().length-5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
