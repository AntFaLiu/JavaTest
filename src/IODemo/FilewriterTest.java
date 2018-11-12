package IODemo;

import java.io.FileWriter;
import java.io.IOException;

public class FilewriterTest {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /**
         * 创建一个可以往文件中写入字符数据的字符流输出流对象
         * 创建时必须明确文件的目的地
         * 如果文件不存在，这回自动创建。如果文件存在，则会覆盖。
         * 当路径错误时会抛异常
         *
         * 当在创建时加入true参数，回实现对文件的续写。
         */
//        FileWriter fw = new FileWriter("E:/demo1.txt",false);//标志是否追加
//        /**
//         * 调用该对象的write方法，向文件写入字符。
//         *
//         * 其实写入到了临时存储缓冲区中
//         */
////      fw.write("hello \r\nworld!");//windows中的换行为\r\n    unix下为\r。
//        fw.write("aello"+LINE_SEPARATOR+"world!");
//        fw.write("hahaha");
//        /**
//         * 进行刷新，将字符写到目的地中。
//         */
////      fw.flush();
//        /**
//         * 关闭流，关闭资源。在关闭前会调用flush方法 刷新缓冲区。关闭后在写的话，会抛IOException
//         */
//        fw.close();

        FileWriter fw = null;
        try {
            fw = new FileWriter("E:/de.tx", true);
            fw.write("hello" + LINE_SEPARATOR + "world!");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败！"); //关于FileWriter的的异常处理。
                }
        }
    }
}
