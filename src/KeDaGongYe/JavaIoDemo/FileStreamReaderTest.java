package KeDaGongYe.JavaIoDemo;

import java.io.*;

//使用reader
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
            BufferedReader bufferedReader = new BufferedReader(input);
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(new String(line.getBytes("GBK"), "utf-8"));
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
            br.skip(3);
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
//为什么结果中还是有部分乱码呢？
//        问题出在FileReader读取文件的过程中，FileReader继承了InputStreamReader，
// 但并没有实现父类中带字符集参数的构造函数，所以FileReader只能按系统默认的字符集来解码，然后在UTF-8
//        -> GBK-> UTF-8的过程中编码出现损失，造成结果不能还原最初的字符。