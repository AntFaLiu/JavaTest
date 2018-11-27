package IODemo;

import java.io.*;

public class InputStreamTest {
    public static void main(String[] args) {
//        File f = new File("d:" + File.separator+"FileOutputStreamTest.txt");
////        InputStream in= null;
////        try {
////            in = new FileInputStream(f);
////            byte[] b=new byte[1024];
////            int len=in.read(b);
////            in.close();
////            System.out.println(new String(b,0,len));
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }

        File f = new File("d:" + File.separator + "FileOutputStreamTest.txt");
        InputStream in = null;
        try {
            in = new FileInputStream(f);
            //方式1
//            byte[] b = new byte[(int) f.length()];
//            in.read(b);
            //方式2：一个字节一个字节读入
//            byte[] b = new byte[(int) f.length()];
//            for (int i = 0; i < b.length; i++) {
//                b[i] = (byte) in.read();
//            }
//            //方式3：以上情况只适合知道输入文件的大小，不知道的话用如下方法：
            byte[] b = new byte[1024];
            int temp = 0;
            int len = 0;
            while ((temp = in.read()) != -1) {//-1为文件读完的标志
                b[len] = (byte) temp;
                len++;
            }
            in.close();
            System.out.println(new String(b));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
