package caiYuan.io;

import java.io.*;

public class LuanMaTest {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("E:/luanma.txt");
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            System.out.println(new String(buffer, "GBK"));

            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (new FileInputStream("E:/luanma.txt"), "gbk"));
            String str = reader.readLine();
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
