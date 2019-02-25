package keDaGongYe.JavaIoDemo;

import java.io.*;

public class StreanAndErTest {
    public static void main(String[] args) {
        String path = "e:/StreanAndErTest.txt";
        String content = "图论科技  刘宇鹏";
        String charName = "utf-8";
        write(path,content,charName);
        read(path,charName);
    }

    public static void write(String path, String content,String charName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true), charName));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String path,String charName) {
        try {
            String line;
            String result = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), charName));
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
