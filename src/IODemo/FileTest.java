package IODemo;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        //创建一个新文件
//        File f1 = new File("FileTest1.txt");//所指的文件是在当前目录下创建的FileTest1.txt
//        try {
//            f1.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        File f2 = new File("D:/dir1", "FileTest2.txt");//D:\dir1目录事先必须存在，否则异常
//        try {
//            f2.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        File f4 = new File("D:/dir1");
//        File f5 = new File(f4, "FileTest5.txt"); //在如果 \dir3目录不存在使用f4.mkdir()先创建
//        try {
//            f4.mkdir(); //创建文件夹
//            f5.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//  删除文件
//        File f=new File("d:"+File.separator+"LypTest.txt");
//        if(f.exists()){//判断文件存不存在，如不存在就不用删除了
//            f.delete();
//        }


        //综合创建，删除文件的操作   给定一个路径，如果此文件存在，则删除，如果不存在，则创建
//        File f = new File("d:" + File.separator + "test.txt");
//        if (f.exists()) {
//            f.delete();
//        } else {
//            try {
//                f.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        列出指定目录的全部文件
//        如果现在给出了一个目录，则可以直接列出目录中的内容。但是列出的方法在File类中存在两个：
//        以字符串数组的形式返回：public String[] list()
//        以File数组的形式返回：public File[] listFiles()
//        以File数组的形式列出系统所有的根路径，这是一个静态方法：static File[] listRoots() 操作一：使用list()列出全部内容
//        File f=new File("d:"+File.separator);
//        String[] str=f.list();
//        for(String s:str){
//            System.out.println(s);
//        }

//        使用listFiles()列出
//        File f=new File("d:"+File.separator);
//        File[] files=f.listFiles();
//        for(File file:files){
//            System.out.println(file);
//        }

//        判断一个给定的路径是否是目录
        File f=new File("d:"+File.separator);
        if(f.isDirectory()){
            System.out.println(f.getPath()+"是目录");
        }else{
            System.out.println(f.getPath()+"不是目录");
        }

//        列出指定目录的全部内容   使用递归的形式打印所有内容
        File w = new File("d:" + File.separator);
        print(w);
    }

    public static void print(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                if (files != null) {
                    for (File file : files) {
                        print(file);
                    }
                }
            } else {
                System.out.println(f);
            }
        }
    }
}
