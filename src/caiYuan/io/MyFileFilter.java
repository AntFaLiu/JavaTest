package caiYuan.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;


//一级目录下 文件   有目录 继续去遍历
class MyFileNmaeFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        //lyp  reader     InPutStream()
        System.out.println("dir： " + dir + "   name: " + name);
        return (new File(dir, name).isDirectory()) ||
                (new File(dir, name)).isFile() && name.endsWith(".java");
    }
}

class FileFilterWorker implements FileFilter {

    @Override
    public boolean accept(File pathname) {

        String filename = pathname.getName();
        return (pathname.isDirectory()) ||
                (pathname.isFile() && filename.endsWith(".java"));
    }
}

public class MyFileFilter {
    public static void main(String[] args) {
        String fileName = "E:/java";
        fileNameMyFilter(fileName);
//        fileMyFilter(fileName);
    }

    public static void fileNameMyFilter(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            File[] files = file.listFiles(new MyFileNmaeFileFilter());
            //这个数组  1.符合我们过滤条件的文件   2.目录 递归
            if (files != null) {
                int length = files.length;
                for (int j = 0; j < length; j++) {
                    if (!files[j].isHidden()) {
                        if (files[j].isFile()) {
                            //System.out.println(files[j].getName());
                        } else {
                            fileNameMyFilter(files[j].getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    public static void fileMyFilter(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            File[] files = file.listFiles(new FileFilterWorker());
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


