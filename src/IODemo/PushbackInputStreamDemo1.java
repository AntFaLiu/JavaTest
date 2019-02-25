package ioDemo;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

//public class PushbackInputStreamDemo1 {
//    public static void main(String[] args) throws exception {
//        String s = "abcdefg";
//        /*
//         * PushbackInputStream pbin = new PushbackInputStream(in)
//         * 这个构造函数创建的对象一次只能回推一个字节
//         */
//        try (ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
//             PushbackInputStream pbin = new PushbackInputStream(in)) {
//            int n;
//            while ((n = pbin.read()) != -1) {
//                System.out.print((char) n);
//                if('b' == n) pbin.unread('U');
//            }
//        }
//    }
//}
//打印结果
//abUcdefg
//        Process finished with exit code 0

public class PushbackInputStreamDemo1 {
    public static void main(String[] args) throws Exception {
        String s = "abcdefg";
        /*
         * PushbackInputStream pbin = new PushbackInputStream(in,4)
         * 这个构造函数创建的对象一次可以回推一个缓存
         */
        try (ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
             PushbackInputStream pbin = new PushbackInputStream(in, 4)) {
            int n;
            byte[] buffer = new byte[5];
            while ((n = pbin.read(buffer)) != -1) {
                System.out.println(new String(buffer));
                //取回推缓存中的一部分数据
                if (new String(buffer).equals("abcde")){
                   pbin.unread(buffer, 1, 3);//向后推到off位置然后，往后读len个字符 但是需要注意的是len必须小于等于你已经读过的字符个数
                }
                buffer = new byte[5];
            }
        }
    }
}