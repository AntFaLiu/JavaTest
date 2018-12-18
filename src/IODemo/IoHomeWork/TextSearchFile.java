package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextSearchFile {
    static int countFiles = 0; // 统计文件个数的变量
    static int countFolders = 0;// 统计文件夹的变量

    public static void main(String[] args) {
        String fileName = "E:/JavaIOTet.txt";
        String fileDir = "E:/Java";
        String keyword = "temp";
        SerarchByStr(fileDir, keyword);
        File file = new File(fileName);
        judeFileExists(file);
        File dir = new File(fileDir);
        judeDirExists(dir);
    }

    public static File[] searchFile(File folder, final String keyword) {// 递归查找包含关键字的文件
        // 运用内部匿名类获得文件
        File[] subFolders = folder.listFiles(new FileFilter() {
            public boolean accept(File pathname) { // 实现FileFilter类accept方法
                if (pathname.isFile()) // 如果是文件
                    countFiles++;
                else // 如果是目录
                    countFolders++;
                if (pathname.isDirectory() || (pathname.isFile() && pathname.getName().contains(keyword))) // 目录或文件包含关键字
                    return true;
                return false;

            }
        });
        List<File> result = new ArrayList<File>();

        for (int i = 0; i < subFolders.length; i++) {
            if (subFolders[i].isFile()) {
                result.add(subFolders[i]);
            } else {
                File[] foldResult = searchFile(subFolders[i], keyword); // 循环递归
                for (int j = 0; j < foldResult.length; j++) {
                    result.add(foldResult[j]);
                }
            }
        }
        // 声明文件数组，长度为集合的长度
        File files[] = new File[result.size()];
        result.toArray(files);
        return files;

    }

    // 判断文件是否存在
    public static void judeFileExists(File file) {

        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // 判断文件夹是否存在
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }


    public static void SerarchByStr(String fileName, String keyword) { //查找文件名中含特殊字符的文件
        File folder = new File(fileName); // 默认目录  **是一级文件目录

        if (!folder.isDirectory()) { // 如果文件夹不存在
            System.out.println("目录不存在：" + folder.getAbsolutePath());
            return;
        }
        File[] result = searchFile(folder, keyword);// 调用方法获得文件数组

        System.out.println("在" + folder + "以及所有子文件时查找对象" + keyword);
        System.out.println("查找了" + countFiles + "个文件，" + countFolders + "个文件夹，共找到" + result.length + "个符合条件的文件");
        for (int i = 0; i < result.length; i++) {
            File file = result[i];
            System.out.println(file.getAbsolutePath() + " ");//显示文件绝对路径
        }
    }

}
