package caiYuan.io.IoHomeWork;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("D:", "d.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        file.delete();
    }
}
