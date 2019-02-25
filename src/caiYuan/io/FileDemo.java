package caiYuan.io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        File f = new File("src\\a.txt");

        System.out.println("获取文件名：" + f.getName());

        System.out.println("获取相对路径：" + f.getPath());

        f.createNewFile();
        /** 当上面两条输出语句的绝对路径下没有a.txt这个文件时，也是输出这个结果，因为他获取的是当前对象f的值*/
        System.out.println(f.exists());

        System.out.println("获取绝对路径：" + f.getAbsolutePath());//

        System.out.println("获取文件的大小(字节为单位)：" + f.length());

        System.out.println("获取文件的父路径：" + f.getParent());
        long lm = f.lastModified();   //最后一次修改时间

        Date date = new Date(lm);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        System.out.println("获取文件最后一次修改的时间：" + sdf.format(date));
    }
}
