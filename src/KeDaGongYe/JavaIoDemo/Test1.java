package keDaGongYe.JavaIoDemo;

import java.io.*;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\bbb.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
        Scanner in = new Scanner(System.in);
        String  s = in.nextLine();
        bufferedWriter.write(s.toCharArray());
        bufferedWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
        char[] b = new char[20];
        bufferedReader.read(b);
        System.out.println(b);
    }

}
