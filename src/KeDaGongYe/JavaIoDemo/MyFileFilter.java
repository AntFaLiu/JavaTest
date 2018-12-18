package KeDaGongYe.JavaIoDemo;

import java.io.File;
import java.io.FilenameFilter;

class MyFileNmaeFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return (new File(dir, name).isDirectory()) ||
                (new File(dir, name)).isFile() && name.endsWith(".java");
    }
}

public class MyFileFilter {
    public static void main(String[] args) {
        String fileName = "E:/java";
        fileMyFilter(fileName);
    }
    public static void fileMyFilter(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            File[] files = file.listFiles(new MyFileNmaeFileFilter());
            if (files != null) {
                int length = files.length;
                for (int j = 0; j < length; j++) {
                    if (!files[j].isHidden()) {
                        if (files[j].isFile()) {
                            System.out.println(files[j].getName());
                        } else {
                            fileMyFilter(files[j].getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}


