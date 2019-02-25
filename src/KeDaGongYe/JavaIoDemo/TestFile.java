package keDaGongYe.JavaIoDemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestFile {
    public static void main(String[] args) {
        File f = new File("E:\\");
        List<File> result = new ArrayList<File>();
        fileJava(f, result);

    }

    public static void fileJava(File dir, List<File> result) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileJava(file, result);//递归调用
                } else {
                    if (file.getName().endsWith(".java")) {
                        System.out.println(file);
                    }
                }
            }
        }
    }
}

