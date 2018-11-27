package IODemo;

import java.io.*;

public class PrintWriterTest {
    public static void main(String[] args) throws IOException {
        /*
         * 创建输出流，将信息写入指定的文件中   会将所有的数据转换为字符串存入文件中
         */
        char []arr = {'c','d','w'};
        OutputStream os = new FileOutputStream("io1.txt");
        PrintWriter pw = new PrintWriter(os);
        pw.write("尽快发货代收快件");
        pw.append(" 好久额~");
        pw.println("火鸡肉");
        pw.write("发给我是");
        pw.println(arr);
        pw.println(true);
        //输出流需要在读取之前关闭保存
        pw.close();
        os.close();

        /*
         * 创建输入流，将信息读到控制台
         */
        InputStream is = new FileInputStream("io1.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String info = null;
        info = br.readLine();
        while (info != null) {
            System.out.println(info);
            info = br.readLine();
        }
        br.close();
        isr.close();
        is.close();
    }
}
