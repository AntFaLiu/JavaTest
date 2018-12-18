package KeDaGongYe.JavaIoDemo;

import IODemo.InputStreamReader;

import java.io.*;

//结果说明：
//        (01) testWrite() 的作用是将“内容写入到输出流”。写入的时候，会将写入的内容转换utf-8编码并写入。
//        (02) testRead() 的作用是将“内容读取到输入流”。读取的时候，会将内容转换成utf-8的内容转换成字节并读出
//如何操作中文字符
public class StreamConverter {

    private static final String FileName = "E:/file1.txt";
    private static final String CharsetName = "utf-8";
    //private static final String CharsetName = "gb2312";

    public static void main(String[] args) {
        testWrite();
        testRead();

//        writeFileBufferedWriter(FileName,"刘宇鹏");
//        String str = readFileBufferedReader(FileName);
//        System.out.println(str);
    }

    /**
     * OutputStreamWriter 演示函数   //转换流
     */
    private static void testWrite() {
        try {
            // 创建文件“file.txt”对应File对象
            File file = new File(FileName);
            // 创建FileOutputStream对应OutputStreamWriter：将字节流转换为字符流，即写入out1的数据会自动由字节转换为字符。
            OutputStreamWriter out1 = new OutputStreamWriter(new FileOutputStream(file), CharsetName);   //CharsetName需要前后统一
            // 写入10个汉字
            out1.write("字节流转为字符流示例");
            // 向“文件中”写入"0123456789"+换行符
            out1.write("0123456789\n");
            out1.close();
        } catch (IOException e) {
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
            IODemo.InputStreamReader in1 = new InputStreamReader(new FileInputStream(file), "utf-8");
            // 测试read()，从中读取一个字符
//            char c1 = (char) in1.read();
//            System.out.println("c1=" + c1);
            // 测试skip(long byteCount)，跳过6个字符
            in1.skip(2);
            // 测试read(char[] cbuf, int off, int len)
            char[] buf = new char[10];
            in1.read(buf, 0, buf.length);
            System.out.println("buf=" + (new String(buf)));
            in1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileBufferedWriter(String fileName, String content){
        FileWriter output = null;
        BufferedWriter writer = null;
        try{
            output = new FileWriter(fileName);
            writer = new BufferedWriter(output);
            writer.write(content);
            writer.append("ddddd");
            writer.write("abcderghlmnopqrswyz",5,6);
            writer.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(null != output){
                try {
                    output.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读文件
     * @param fileName
     * @return
     */
    public static String readFileBufferedReader(String fileName){
        StringBuffer sb = new StringBuffer("");
        FileReader input = null;
        BufferedReader reader = null;
        try{
            input = new FileReader(fileName);
            reader = new BufferedReader(input);
            //System.out.println(reader.ready());
            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(null != input){
                try {
                    input.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

