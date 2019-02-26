package ioDemo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class randomAccessFile {

    public static void main(String[] args) throws IOException {
        File demo = new File("E:/");
        if (!demo.exists())
            demo.mkdir();
        File file = new File(demo, "raf.txt");
        if (!file.exists())
            file.createNewFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        System.out.println(raf.getFilePointer());

        //raf.write('A');//只写一个字节
        //raf.writeChars("刘宇鹏");
        String str = "刘宇鹏";

        byte[] strGbk = str.getBytes("gbk");
        raf.write(strGbk);
//        raf.writeBytes("刘宇鹏","UTF-8");
        System.out.println(raf.getFilePointer());
        raf.write('B');

        int i = 0x7fffffff;
        //用write方法一次只能写一个字节，把i整个写进去要write四次
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);
        System.out.println("raf.getFilePointer():" + raf.getFilePointer());

        raf.writeInt(i);
        String s = "李";
        byte[] gbk = s.getBytes("gbk");
        raf.write(gbk);
        System.out.println(raf.length());

        //读文件，必须把指针移到头部
        raf.seek(0);
        //一次性读取，把文件中的内容一次读到字节数组里
        byte[] buf = new byte[(int) raf.length()];
        raf.read(buf);
        System.out.println(Arrays.toString(buf));
        for (byte b : buf) {
            System.out.println(Integer.toHexString(b & 0xff) + " ");
        }
        raf.close();
    }
}
