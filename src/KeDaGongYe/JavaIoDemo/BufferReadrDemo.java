package KeDaGongYe.JavaIoDemo;
import java.io.*;
import java.nio.CharBuffer;

public class BufferReadrDemo {
    public static void main(String[] args) {
        writer();
    }

    public static void writer() {
        //字符流-->字节流
        try {
            BufferedReader br = new BufferedReader(new FileReader("readin.txt"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

