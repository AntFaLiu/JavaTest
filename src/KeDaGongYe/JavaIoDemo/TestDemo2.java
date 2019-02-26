package keDaGongYe.JavaIoDemo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestDemo2 {
    public static void main(String[] args) throws FileNotFoundException {
        // countNum("E:\\java\\代码\\20161007\\src\\com\\example\\test2\\TestDemo.java");
//        BufferedInputStreamTest bufferedInputStream = new BufferedInputStreamTest(new FileInputStream("E:\\java\\代码\\20161007\\src\\com\\example\\test2\\TestDemo.java"));
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(""));
//        BufferedReader reader = new BufferedReader(new FileReader("E:\\java\\代码\\20161007\\src\\com\\example\\test2\\TestDemo.java"));
        BufferedReader bufferedReader = new BufferedReader(new FileReader("d:\\aaa.txt"));
        char[] c = new char[5];
        for (int i = 0; i < 5; i++) {
            try {
                char elem = (char) bufferedReader.read();
                System.out.print(elem);
                if(i == 0){
                    bufferedReader.mark(4);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        try {
            bufferedReader.reset();
            bufferedReader.read(c);
            System.out.print(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        try {
            bufferedReader.skip(5);
            bufferedReader.read(c, 0, c.length);
            System.out.println(c);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void countNum(String path) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bufis = new BufferedInputStream(fis);
            Map<Character, Integer> map = new HashMap<>();
            byte[] buf = new byte[1024];
            int len;
            while ((len = bufis.read(buf)) != -1) {
                for (int i = 0; i < len; i++) {
                    if ((buf[i] >= 'a' && buf[i] <= 'z') || (buf[i] >= 'A' && buf[i] <= 'Z')) {
                        Integer count = map.get((char) buf[i]);
                        map.put((char) buf[i], null == count ? 1 : count + 1);
                    }
                }
            }

            for (Map.Entry<Character, Integer> enter : map.entrySet()) {
                System.out.println("字母：" + enter.getKey() + "出现：" + enter.getValue() + "次");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy(File file1, File file2) throws IOException {
        FileInputStream fi1 = new FileInputStream(file1);
        FileOutputStream fo1 = new FileOutputStream(file2);
        int len = 0;
        while (len >= 0) {
            len = fi1.read();
            fo1.write(len);
        }
        fo1.close();
        fi1.close();
    }

}
