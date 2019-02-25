package keDaGongYe.JavaIoDemo;

import java.io.*;

public class BufferedInputStreamTest {
    public static void main(String[] args) {

        String path = "E:/java/BufferedInputStreamTest.txt";
        String content = "hello world";
        //writeToFile(path, content);
        readFromFile(path);
    }

    public static void readFromFile(String filename) {
        BufferedInputStream bufferedInput = null;
        try {
            bufferedInput = new BufferedInputStream(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(String filename, String content) {
        BufferedOutputStream bufferedOutput = null;
        try {
            bufferedOutput = new BufferedOutputStream(new FileOutputStream(filename));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
