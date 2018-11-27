package IODemo;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
//JAVA中mark()和reset()用法的通俗理解
//mark就像书签一样，在这个BufferedReader对应的buffer里作个标记，以后再调用reset时就可以再回到这个mark过的地方。
//     mark方法有个参数，通过这个整型参数，你告诉系统，希望在读出这么多个字符之前，这个mark保持有效。
//     读过这么多字符之后，系统可以使mark不再有效，而你不能觉得奇怪或怪罪它。这跟buffer有关，如果你需要很长的距离，
//     那么系统就必须分配很大的buffer来保持你的mark。
//public class MarkExample {
//    public static void main(String[] args) {
//
//        try {
//            // 初始化一个字节数组，内有5个字节的数据
//            byte[] bytes={1,2,3,4,5};
//            // 用一个ByteArrayInputStream来读取这个字节数组
//            ByteArrayInputStream in=new ByteArrayInputStream(bytes);
//            // 将ByteArrayInputStream包含在一个BufferedInputStream，并初始化缓冲区大小为2。
//            BufferedInputStream bis=new BufferedInputStream(in,1);  //缓冲区大小对mark、reset的作用
//            // 读取字节1
//            System.out.print(bis.read()+",");
//            // 在字节2处做标记，同时设置readlimit参数为1
//            // 根据JAVA文档mark以后最多只能读取1个字节，否则mark标记失效，但实际运行结果不是这样
//            System.out.println("mark");
//            bis.mark(1);
//
//            /*
//             * 连续读取两个字节，超过了readlimit的大小，mark标记仍有效
//             */
//            // 连续读取两个字节
//            System.out.print(bis.read()+",");
//            System.out.print(bis.read()+",");
//            // 调用reset方法，未发生异常，说明mark标记仍有效。
//            // 因为，虽然readlimit参数为1，但是这个BufferedInputStream类的缓冲区大小为2，
//            // 所以允许读取2字节
//            System.out.println("reset");
//            bis.reset();
//
//            /*
//             * 连续读取3个字节，超过了缓冲区大小，mark标记失效。
//             * 在这个例子中BufferedInputStream类的缓冲区大小大于readlimit,
//             * mark标记由缓冲区大小决定
//             */
//            // reset重置后连续读取3个字节，超过了BufferedInputStream类的缓冲区大小
//            System.out.print(bis.read()+",");
//            System.out.print(bis.read()+",");
//            System.out.print(bis.read()+",");
//            // 再次调用reset重置，抛出异常，说明mark后读取3个字节，mark标记失效
//            System.out.println("reset again");
////            bis.reset();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}
