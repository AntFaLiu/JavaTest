package caiYuan.io.IoHomeWork;

import java.io.File;
import java.io.IOException;

public class TestFille1 {
    public static void main(String[] args) {
        File IOTest = new File("E:/");
        File f = new File("D:", "Hello.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.renameTo(new File(IOTest.getPath() +
                File.separator + f.getName()));
        //renameTo 操作中不允许的一种移动 方式

    }
}
