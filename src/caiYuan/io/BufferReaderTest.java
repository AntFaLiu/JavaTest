package caiYuan.io;

import java.io.*;

public class BufferReaderTest {
    public static void main(String[] args) {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new FileWriter("E:/BufferReaderTest.txt"),5);
            String str = "图论科技";  //UT8F-8
            writer.write(str);
            writer.flush();
            reader = new BufferedReader(new FileReader("E:/BufferReaderTest.txt"));
            String line = null;  //
            while ((line = reader.readLine()) != null) {
                //直到将所有的内容读完
            }
            System.out.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
