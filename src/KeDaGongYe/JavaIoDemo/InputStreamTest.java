package keDaGongYe.JavaIoDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamTest {
    public static void main(String[] args) {
        File f = new File("d:" + File.separator + "FileOutputStreamTest.txt");
//        FileOutputStream outputStream = null;
//        try {
//            String str = "Hello world";
//            outputStream = new FileOutputStream(f,true);
//            outputStream.write(str.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
            byte[] bytes = new byte[1024];  //字节数组  --  String
            int len = 5;
            int off = 0;
            while ((inputStream.read(bytes)) != -1) {
                System.out.print(new String(bytes,"GBK"));  // GBK  UTF-8
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
