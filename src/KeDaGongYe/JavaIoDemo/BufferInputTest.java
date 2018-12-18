package KeDaGongYe.JavaIoDemo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class BufferInputTest {
    public static void main(String[] args) {
        testBufferedInput();
    }
    public static void testBufferedInput() {
        try {
            /**
             * 建立输入流 BufferedInputStream, 缓冲区大小为8
             * buffer.txt内容为
             * abcdefghij
             */
            InputStream in = new BufferedInputStream(new FileInputStream("E:/JavaIOTestTwo.txt"), 10);
            /*从字节流中读取5个字节*/
            byte [] tmp = new byte[10];
            in.read(tmp, 0, 5);

            System.out.println("字节流的前5个字节为: " + new String(tmp));
            /*标记测试*/
            in.mark(3);
            /*读取5个字节*/
            in.read(tmp, 0, 4);
            System.out.println("字节流中第6到10个字节为: " +  new String(tmp));
            /*reset*/
            in.reset();
            System.out.printf("reset后读取的第一个字节为: %c" , in.read());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
