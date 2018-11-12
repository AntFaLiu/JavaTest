package IODemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

    public static void main(String[] args) {

        String source = "E:/JavaIOTest.txt";
        randomReader(source,10);  //只能移动双数位
//        randomWrite(source);
        randomInsert(source, 20, "fhdfjdsh");
//        write(source);

    }

    public static void randomReader(String path, int postion) {

        try {
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            //获取RandomAccessFile对象文件指针的位置，初始位置是0
            System.out.println("RandomAccessFile文件指针的初始位置:" + raf.getFilePointer());
            raf.seek(postion);//移动文件指针位置
            byte[] buff = new byte[1024];
            //用于保存实际读取的字节数
            int hasRead = 0;
            //循环读取
            while ((hasRead = raf.read(buff)) > 0) {
                //打印读取的内容,并将字节转为字符串输入
                System.out.println(new String(buff, 0, hasRead,"gbk"));//输出中文
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 追加数据
     */

    public static void randomWrite(String path) {

        try {
            /**以读写的方式建立一个RandomAccessFile对象**/
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            //将记录指针移动到文件最后
            raf.seek(raf.length());
            raf.write("我是追加的 ".getBytes("gbk"));   //传中文应序列化
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 任意位置插入数据
     *
     * @param path
     * @param points
     * @param insertContent
     */

    public static void randomInsert(String path, long points, String insertContent) {

        try {

            File tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();// 在JVM退出时删除
            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            // 创建一个临时文件夹来保存插入点后的数据

            FileOutputStream tmpOut = new FileOutputStream(tmp);
            FileInputStream tmpIn = new FileInputStream(tmp);
            raf.seek(points);

            /** 将插入点后的内容读入临时文件夹 **/


            byte[] buff = new byte[1024];
            // 用于保存临时读取的字节数

            int hasRead = 0;
            // 循环读取插入点后的内容

            while ((hasRead = raf.read(buff)) > 0) {
                // 将读取的数据写入临时文件中
                tmpOut.write(buff, 0, hasRead);

            }


            // 插入需要指定添加的数据
            raf.seek(0);
            raf.seek(points);// 返回原来的插入处
            // 追加需要追加的内容
            byte[] strGbk = insertContent.getBytes("gbk");
            raf.write(strGbk);
            // 最后追加临时文件中的内容
            while ((hasRead = tmpIn.read(buff)) > 0) {
                raf.write(buff, 0, hasRead);
            }
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
