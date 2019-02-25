package ioDemo;

import java.io.*;
//转换流
public class StreamConverterTest {

    private static final String FileName = "e.txt";
    private static final String CharsetName = "utf-8";
    //private static final String CharsetName = "gb2312";
    public static void main(String[] args) {
        testWrite();
        testRead();
    }

    /**
     * OutputStreamWriter 演示函数
     *
     */
    private static void testWrite() {
        try {
            // 创建文件“file.txt”对应File对象
            File file = new File(FileName);
            // 创建FileOutputStream对应OutputStreamWriter：将字节流转换为字符流，即写入out的数据会自动由字节转换为字符。
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), CharsetName);
            // 写入10个汉字
            out.write("字节流转为字符流示例");
            // 向“文件中”写入"0123456789"+换行符
            out.write("0123456789\n");
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * InputStreamReader 演示程序
     */
    private static void testRead() {
        try {
            // 方法1：新建FileInputStream对象
            // 新建文件“file.txt”对应File对象
            File file = new File(FileName);
            InputStreamReader in = new InputStreamReader(new FileInputStream(file), CharsetName);
            // 测试read()，从中读取一个字符
            char c1 = (char)in.read();
            System.out.println("c1="+c1);
            // 测试skip(long byteCount)，跳过6个字符
            in.skip(6);
            // 测试read(char[] cbuf, int off, int len)
            char[] buf = new char[10];
            in.read(buf, 0, buf.length);
            System.out.println("buf="+(new String(buf)));
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
