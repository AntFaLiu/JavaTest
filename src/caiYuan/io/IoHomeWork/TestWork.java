package caiYuan.io.IoHomeWork;

import java.io.File;
import java.io.IOException;

public class TestWork {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\HelloWord.txt");
        file.createNewFile();
        if (file.isFile()) {
            System.out.println("是文件");
        } else if (file.isDirectory()) {
            System.out.println("是目录");
        }
        File file1 = new File("d:\\IOTest");
        file1.mkdirs();
        File file2 = new File("d:\\IOTest\\HelloWord.txt");
        boolean b = file.renameTo(file2);
        System.out.println(b);
    }

}
