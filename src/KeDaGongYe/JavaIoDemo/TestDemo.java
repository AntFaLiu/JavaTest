package KeDaGongYe.JavaIoDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDemo {
    public static void main(String[] args) throws IOException {
//        String filePath = "E:\\Hello.txt";
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        String msg = "HelloWorld你好世界";
//        byte[] bytes = msg.getBytes();
//        fileOutputStream.write(bytes);
//        fileOutputStream.close();
//
//        FileInputStream fileInputStream = new FileInputStream(file);
//        int read = -1;
//        byte[] b = new byte[1024];
//        int tmp;
//        int len = 0;
//        while((tmp = fileInputStream.read()) != read) {
//            //(char)tmp;
//            b[len] =(byte)tmp;
//            len++;
//        }
//        fileInputStream.close();
//        System.out.println(new String(b,0,len));
        String s = "HelloJavaWord你好世界！";
        File file = new File("d:\\HellowWord.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] brr = new byte[1024];
        fileInputStream.read(brr);
        System.out.println(new String(brr,"utf-8"));
        fileInputStream.close();
        fileOutputStream.close();


    }
}
