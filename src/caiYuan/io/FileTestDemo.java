package caiYuan.io;

import java.io.File;
import java.io.IOException;

public class FileTestDemo {
    public static void main(String[] args) {
        File file = new File("E:", "java\\FileTestDemo.txt");

        File file1 = new File("E:/java/FileTestDemo1.txt");
//
        File tmp = new File("E:/java");
//
        File file2 = new File(tmp, "FileTestDemo2.txt");
//
        File tmp1 = new File("E:/java/FileTestDemo3.txt");

        File file3 = new File(tmp1.toURI());
//
        File file4 = new File("E:/java" + File.separator + "FileTestDemo4.txt");

        //构造函数

//        如何创建文件
        try {
//            file.createNewFile();
//            file1.createNewFile();
            file2.createNewFile();
//            file3.createNewFile();
//            file4.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        创建一个目录
        File mk = new File("E:/IOTest");
        mk.mkdir();
        File mk1 = new File("E:/IOTest1/Test");
//        mk1.mkdir();
        mk1.mkdirs();
//
        File f = new File("E:/Java");
        //文件移动
        file2.renameTo(f);


//        String[] files = f.list();
//        for (String file : files) {
//            System.out.println(file);
//        }
//        File[] files1 = f.listFiles();
//        for (File file : files1) {
//            System.out.println(file);
//        }
    }
}
