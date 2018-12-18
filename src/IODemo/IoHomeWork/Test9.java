package IODemo.IoHomeWork;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test9 {
    /**
     * 统计一个文件calcCharNum2.txt（见附件）中各个字母出现次数：
     * A(8),B(16),C(10)...,a(12),b(10),c(3)....中(5),国(6)，
     * 括号内代表字符出现次数;
     *
     * 程序分析：
     * 1.这出现中文字符，依然只能用字符流来读取文件
     * 2.不能保存相同的主键值，可以使用HashMap：key-value来实现
     * 3.先获得该key的value，如果存在key的话value的值加1
     * */
    public static void main(String[] args) {
        // 文件路径
        File file = new File("E:\\java\\代码\\20160924\\src\\com\\example\\test\\WorkAroundDemo.java");
        // 创建集合HashMap类存放要保存的key-value
        HashMap<String,Integer> map = new HashMap<>();

        try {
            // 创建字符流
            FileReader fr = new FileReader(file);
            // 每次读取的字符长度
            int len = 0;
            int count=0;//出现该字符的次数
            while ((len = fr.read()) != -1) {
                // 每次获取到的字母
                char c = (char) len;
                if ( String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
                    if (map.containsKey(c + "")) {
                        count = map.get(c + "");
                        map.put(c + "", count + 1);
                    } else {
                        map.put(c + "", 1);
                    }
                }
            }

            // 读完后把结果打印出来
            Iterator<Map.Entry<String,Integer>> iterator = map.entrySet().iterator();

            //迭代器的使用
            while (iterator.hasNext()) {
                Map.Entry<String,Integer> entry = iterator.next();
                System.out.print(entry.getKey() + "(" + entry.getValue()+ ") \t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
