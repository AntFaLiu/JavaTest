package ioDemo;

import java.io.StringReader;

public class StringInputFile {
    public static String read(String content) throws Exception {
        StringReader in = new StringReader(content);

        int s = -1;
        String sb = "";
        while ((s = in.read()) != -1) {
            sb += (char) s;
        }
        in.close();
        return sb;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(read("iu阿无任何金融和i百分百的是部分v佛教圣地愤怒并决定是否能不觉得舒服吧？"));
    }
}
