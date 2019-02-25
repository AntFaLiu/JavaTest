package caiYuan.io;

import java.io.*;

public class BufferedReaderTest {
    public static void main(String[] args) {
        String file = "E:/Java/BufferedReaderTest.txt";
        String content = "tulunkeji";
        writeFileBufferedWriter(file, content);
        String result = readFileBufferedReader(file);
        System.out.println("文件内容： " + result);
    }

    public static void writeFileBufferedWriter(String fileName, String content) {
        FileWriter output = null;
        BufferedWriter writer = null;
        try {
            output = new FileWriter(fileName);
            writer = new BufferedWriter(output);
            writer.write(content);
//            writer.append("ddddd");
//            writer.write("abcderghlmnopqrswyz",5,6);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (null != output) {
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
     *
     * @param fileName
     * @return
     */
    public static String readFileBufferedReader(String fileName) {
        StringBuffer sb = new StringBuffer("");
        FileReader input = null;
        BufferedReader reader = null;
        try {
            input = new FileReader(fileName);
            reader = new BufferedReader(input);
            //System.out.println(reader.ready());
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (null != input) {
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
