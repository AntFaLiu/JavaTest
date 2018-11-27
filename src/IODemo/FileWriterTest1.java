package IODemo;

import java.io.*;

public class FileWriterTest1 {
    public static void main(String[] args) {
//        File f = new File("d:" + File.separator+"FileWriterTest1.txt");
//        Writer out= null;
//        try {
//            out = new FileWriter(f);
//            String str="Hello World";
//            out.write(str);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        File f = new File("d:" + File.separator+"FileWriterTest1.txt");
//        Writer out= null;//追加
//        try {
//            out = new FileWriter(f,true);
//            String str="\r\nHello World";
//            out.write(str);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //也可以用循环方式，判断是否读到底
        File f = new File("d:" + File.separator+"test.txt");
        Reader input= null;
        try {
            input = new FileReader(f);
            char[] c=new char[1024];
            int temp=0;
            int len=0;
            while((temp=input.read())!=-1){
                c[len]=(char) temp;
                len++;
            }
            input.close();
            System.out.println(new String(c,0,len));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
