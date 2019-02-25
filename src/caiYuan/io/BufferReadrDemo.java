package caiYuan.io;
import java.io.*;

//字符流BufferedReader转为字节流System.out
public class BufferReadrDemo {
    public static void main(String[] args) {
        writer();
    }

    public static void writer() {
        //字符流-->字节流
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (new FileInputStream("readin.txt"),"GBK"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter
                    (System.out));  //也可以写入到文件里
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

