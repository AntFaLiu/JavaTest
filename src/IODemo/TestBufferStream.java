package IODemo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestBufferStream {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(
                    "d:/test.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            int c = 0;
            System.out.println("qqqqqqqq: "+(char)bis.read());
            System.out.println("wwwwwwww: "+(char)bis.read());
            bis.mark(1);/*在当前输入流的当前位置上做一个标志，允许最多再读入100个字节*/
            System.out.println("eeeeeeeeeeeeeeee: ");
            for(int i=0;i<=10 && (c=bis.read())!=-1;i++){
                System.out.print((char)c+" ");
            }
            System.out.println();
            System.out.println("rrrrrrrrrrrrrrrrr: ");
            bis.reset();/*把输入指针返回到以前所做的标志处*/
            for(int i=0;i<=10 && (c=bis.read())!=-1;i++){
                System.out.print((char)c+" ");
            }

            bis.close();
            fis.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
