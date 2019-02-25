package caiYuan.io.renameTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//利用rename 更名
public class Rename {
    public static void main(String[] args) {
        File src = new File("E:/java/helloWord.txt");
        FileOutputStream outputStream = null;
        if (!src.exists()) {
            try {
                src.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            String str = "hello word";
            outputStream = new FileOutputStream(src);
            outputStream.write(str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("src.getParent(): " + src.getParent());
        String des = src.getParent() + "/a.txt";
        File dest = new File(des);

        if (dest.exists()) {
            dest.delete();
        }

        if (src.renameTo(dest)) {  //（1）移动文件 （2）更名
            System.out.println("更名成功");
        }
    }
}
