package keDaGongYe.JavaIoDemo;

import java.io.*;

public class FileOutputStreamTest {
    public static void main(String[] args) {
        File f = new File("d:" + File.separator+"FileOutputStreamTest.txt");
        OutputStream out= null;//如果文件不存在会自动创建
        try {
            out = new FileOutputStream(f,true);
            String str="Hello World";
            byte[] b=str.getBytes();
            //一个一个字节输出
//            for(int i=0;i<b.length;i++){
//                out.write(b[i]);
//            }
            out.write(b);//因为是字节流，所以要转化成字节数组进行输出
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
