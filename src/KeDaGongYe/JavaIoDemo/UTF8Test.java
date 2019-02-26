package keDaGongYe.JavaIoDemo;

import java.io.*;

public class UTF8Test {
    public static void main(String[] args) {
        File f  = new File("E:/JavaIoTet.txt");

        // 指定读取文件时以UTF-8的格式读取
        BufferedReader br = null;
        try {
            FileInputStream in = new FileInputStream(f);
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            while(line != null)
            {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
