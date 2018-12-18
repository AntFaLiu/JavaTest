package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileOutputStream;

public class Test5 {
    /**
     * 在程序中写一个"HelloJavaWorld你好世界"输出到操作系统文件Hello.txt文件中
     *
     * 程序分析：文件写入，要用到输出流FileOutputStream
     * */





    public static void main(String[] args) {
        // 向文件D:/Hello.txt，写入内容
        File file = new File("D:/Hello.txt");
        try {
            // 创建输出流
            FileOutputStream fos = new FileOutputStream(file);
            //把String类型的字符串转化为byte数组的数据保存在输出流中
            fos.write("HelloJavaWorld你好世界".getBytes());
            fos.flush();//刷新输出流
            fos.close();//关闭输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
