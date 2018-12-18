package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class Test7 {
    /**
     * 统计一个文件calcCharNum.txt（见附件）中字母'A'和'a'出现的总次数
     *
     * 程序分析：
     * 读取文件用FileInputStream
     * 一次只读一个字节(一个字母就是一个字节)，当字节内容和A或a相等时，相应的数量加1
     * */
    public static void main(String[] args) {
        try {
            //添加文件路径
            File file = new File("E:\\java\\代码\\20160924\\src\\com\\example\\test\\WorkAroundDemo.java");
            //创建文件读取流
            FileInputStream fis = new FileInputStream(file);
            int numA = 0;//字母A的数量
            int numa = 0;//字母a的数量
            int len = 0;//每次读取的字节数量
            while ((len=fis.read())!= -1) {
                System.out.println("len: "+len);
                //统计字母a的数量
                if (new String((char)len+"").equals("a")) {
                    numa++;
                }
                //统计字母A的数量
                if (new String((char)len+"").equals("A")) {
                    numA++;
                }
            }
            //打印出文件内字母的数量
            System.out.println("a的数量是："+numa);
            System.out.println("A的数量是："+numA);
            System.out.println("a和A出现的总次数："+(numA+numa));
            fis.close();//关闭输入流
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
