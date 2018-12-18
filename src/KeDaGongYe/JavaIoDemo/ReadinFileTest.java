package KeDaGongYe.JavaIoDemo;

import IODemo.InputStreamReader;

import java.io.*;

//源：从InputStream，Reader中选择; 因为是键盘录入的是纯文本，所以使用Reader。
// 设备：键盘，所以用System.in; 发现System.in是字节流的操作，与Reader(字符流)矛盾，
// 这时就要用到转换流 InputStreamReader 。为了提高操作效率，使用缓冲技术，选择BufferedReader。
// 目的：从 OutputStream，Writer中选择。 因为是文本文件，所以选择Writer。
// 设备：硬盘上，一个文件，选择FileWriter。 为了提高操作效率，使用缓冲技术，选择BufferedWriter。
public class ReadinFileTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new FileWriter("readin.txt"));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line)) break;
            bufw.write(line);
            bufw.newLine();
        }
        bufw.close();
        bufr.close();
    }
}
