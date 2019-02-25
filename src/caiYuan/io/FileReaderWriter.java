package caiYuan.io;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;

public class FileReaderWriter {
    public static void main(String[] args) {
        File f = new File("d:" + File.separator + "FileWriterTest1.txt");
        //也可以用循环方式，判断是否读到底
        Reader input = null;
        try {
            input = new FileReader(f);
            //数组
            CharBuffer charBuffer = CharBuffer.allocate(5);  // NIO
            input.read(charBuffer);
            System.out.println(Arrays.toString(charBuffer.array()));

            FileWriter writer = new FileWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
