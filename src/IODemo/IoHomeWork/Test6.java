package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test6 {
    /**
     * 拷贝一张图片，从一个目录到另外一个目录下(PS:是拷贝是不是移动)
     *
     * 程序设计思路：
     *  这题不能使用renameTo，
     *  解题步骤：
     * 1、在目的地址创建一个图片文件
     * 2、读取源地址文件的字节流
     * 3、把读取到的字节流写入到目的地址的文件里面
     * 4、刷新输出流，并关闭就可以了
     *
     * @throws Exception
     * */
    public static void main(String[] args) {
        // 本题示范把D盘下的mm.jpg复制到D盘java文件夹里面
        // 源文件地址
        File fileFrom = new File("F:/刘宇鹏/面试/深信服/深信服/IMG_20140716_173903.jpg");
        // 目的文件地址
        File fileTo = new File("E:/java/shenxinfu.jpg");

        // 1、创建目的文件地址
        try {
            if (!fileTo.createNewFile()) {
                System.out.println("创建文件失败！");
            }
            // 2、读取源地址文件的字节流
            FileInputStream fis = new FileInputStream(fileFrom);
            FileOutputStream fos = new FileOutputStream(fileTo);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                // 3、把读取到的字节流写入到目的地址的文件里面
                fos.write(buf);
            }
            // 刷新下输出流
            fos.flush();
            // 关闭输入流和输出流
            fis.close();
            fos.close();
            System.out.println("文件复制成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
