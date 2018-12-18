package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileInputStream;

public class Tset4 {
    /**
     * 从磁盘读取一个文件到内存中，再打印到控制台
     * <p>
     * 程序设计：
     * 1、读取文件用到FileinputSteam
     * 2、把读取的内容不断加入到StringBuffer，
     * 3、再把StringBuffer打印出来就可以
     */
    public static void main(String[] args) {

        // 读取D:\notePad\aa.txt里面的内容
        File file = new File("E:/JavaIOTest.txt");
        try {
            // 创建读取流即输入流
            FileInputStream fis = new FileInputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            StringBuffer sb = new StringBuffer();
            // 把读取的数据添加到StringBuffer里面
            while ((len = fis.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            // 把StringBuffer里面的内容打印出来
            System.out.println(sb);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
