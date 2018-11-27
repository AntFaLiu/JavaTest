package IODemo;

import java.io.*;

public class FileReaderTest {
    public static void main(String[] args) {
        File f = new File("d:" + File.separator+"FileWriterTest1.txt");
        Reader input= null;
        try {
            input = new FileReader(f);
            char[] c=new char[1024];
            int len=input.read(c);
            input.close();
            System.out.println(new String(c,0,len));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
