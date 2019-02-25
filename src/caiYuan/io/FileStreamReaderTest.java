package caiYuan.io;

import java.io.*;

public class FileStreamReaderTest {
    public static void main(String[] args) {
        String filename = "d:/FileWriterTest1.txt";
//        read(filename);
        readStreamReader(filename);
    }

    public static void read(String fileName) {
        File f = new File(fileName);
        Reader input = null;
        try {
            input = new FileReader(f);
            String line = null;
            //BufferedReader bufferedReader = new BufferedReader(input);
            //input.skip(5);
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
            char[] chars = new char[1024];
            while (input.read(chars) > 0){
                System.out.print(chars);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readStreamReader(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}