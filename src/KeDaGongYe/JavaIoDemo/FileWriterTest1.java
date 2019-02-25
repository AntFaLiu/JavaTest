package keDaGongYe.JavaIoDemo;

import java.io.*;

public class FileWriterTest1 {
    public static void main(String[] args) {
        File f = new File("d:" + File.separator + "FileWriterTest1.txt");
        Writer out= null;
        try {
            out = new FileWriter(f);
            out.write('r');
            out.write("abc刘宇鹏");
            out.flush();
            char[] chars = {'a','b'};
            out.write(chars);

            //缓冲区  flush   close
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        //也可以用循环方式，判断是否读到底
//        Reader input = null;
//        try {
//            input = new FileReader(f);
////            input.read();
////            char[] arr = new char[5];
////            input.read(arr);
////            input.read();
//            CharBuffer charBuffer = CharBuffer.allocate(5);  // NIO
//            input.read(charBuffer);
//            for (int i = 0; i < charBuffer.array().length; i++) {
//                System.out.println(charBuffer.get(i));
//            }
////            System.out.println(Arrays.toString(arr));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
