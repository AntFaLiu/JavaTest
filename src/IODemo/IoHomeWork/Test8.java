package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test8 {
    /**
     * 统计一个文件calcCharNum.txt（见附件）中各个字母出现次数：
     * A(8),B(16),C(10)...,a(12),b(10),c(3)....，括号内代表字符出现次数;
     * <p>
     * 程序分析：
     * 1.这里没中文字符，依然可以只用字节流来读取文件
     * 2.不能保存相同的主键值，可以使用HashMap：key-value来实现
     * 3.先获得该key的value，如果存在key的话value的值加1
     */
    public static void main(String[] args) {

        // 文件路径
        File file = new File("E:\\java\\代码\\20160924\\src\\com\\example\\test\\WorkAroundDemo.java");

        try {
            // 创建读取文件的输入流
            // 创建集合HashMap类存放要保存的key-value
            FileInputStream fis = new FileInputStream(file);
            HashMap<String, Integer> map = new HashMap<>();
            // 读取文件
            int len = 0;// 每次读取的文件长度
            int count = 0;
            while ((len = fis.read()) != -1) {
                // 每次获取到的字母
                char c = (char) len;
                if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                    if (map.containsKey(c + "")) {
                        count = map.get(c + "");
                        map.put(c + "", count + 1);
                    } else {
                        map.put(c + "", 1);
                    }
                }
            }
            fis.close();
            // 读完后把结果打印出来
            //迭代器的使用
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                System.out.print(entry.getKey() + "(" + entry.getValue() + ") \t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
