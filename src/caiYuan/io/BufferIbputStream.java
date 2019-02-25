package caiYuan.io;

import java.io.*;

public class BufferIbputStream {
    public static void main(String[] args) {
        String path = "E:/java/BufferedInputStreamTest.txt";
        String content = "hello world";
        writeToFile(path, content);
//        readFromFile(path);
    }

    public static void readFromFile(String filename) {
        BufferedInputStream bufferedInput = null;
        byte[] buffer = new byte[1024];
        byte[] buff = new byte[10];
        try {
            //创建BufferedInputStream 对象
            bufferedInput = new BufferedInputStream(new FileInputStream(filename),5);
            int bytesRead = 0;

            //从文件中按字节读取内容,到文件尾部时read方法将返回-1
            System.out.println(bufferedInput.markSupported());
            System.out.println(bufferedInput.read());
            bufferedInput.read(buff,0,2);
            System.out.println(new String(buff));

            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                //将读取的字节转为字符串对象
                String chunk = new String(buffer, 0, bytesRead);
                System.out.print(chunk);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //关闭 BufferedInputStream
            try {
                if (bufferedInput != null)
                    bufferedInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void writeToFile(String filename, String content) {
        BufferedOutputStream bufferedOutput = null;
        try {
            bufferedOutput = new BufferedOutputStream(new FileOutputStream(filename));
            byte[] brr = content.getBytes();
            bufferedOutput.write('v');
            bufferedOutput.write(brr,2,3);
            bufferedOutput.write(brr);
            bufferedOutput.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
