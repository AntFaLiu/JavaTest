package caiYuan.io.IoHomeWork;

import java.io.File;
import java.io.IOException;

public class Test2 {
    /**
     * 1.
     * <p>
     * 程序分析：
     * 1、文件创建使用File的createNewFile()方法
     * 2、判断是文件用isFile(),判断是目录用isDirectory
     * 3、创建目录用：mkdirs()方法
     * 4、移动文件用：renameTo
     * 5、遍历目录用：list（）方法获得存放文件的数组，foreach遍历的方法把文件打印出来
     */
    public static void main(String[] args) {
        //在电脑D盘下创建一个文件为HelloWorld.txt文件
        File file = new File("D:", "HelloWorld.txt");

        //创建文件，返回一个布尔值
        boolean isCreate;
        try {
            isCreate = file.createNewFile();
            if (isCreate) {
                System.out.println("创建文件成功！");
            } else {
                System.out.println("创建文件失败！文件已经存在");
            }
        } catch (IOException e) {
            System.out.println("创建文件失败！");
        }

        // 判断他是文件还是目录，
        if (file.isFile()) {
            System.out.println("这是一个文件");
        } else {
            System.out.println("这是一个目录");
        }

        //再创建一个目录IOTest
        File file2 = new File("E:/IOTest");
        file2.mkdirs();

        //HelloWorld.txt移动到IOTest目录下去?失败？》
        if (file.renameTo(new File(file2.getPath() + "/" + file.getName()))) {    //文件已存在则返回false
            System.out.println("文件移动成功！");
        } else {
            System.out.println("文件移动失败");
        }

        //遍历IOTest目录下的文件
        String[] arr = file2.list();
        for (String string : arr) {
            System.out.println(string);
        }
    }
}
