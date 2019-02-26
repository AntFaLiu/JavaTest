package ioDemo;

import java.io.*;


//model各个参数详解
// :r 代表以只读方式打开指定文件
// :rw 以读写方式打开指定文件
// :rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
// :rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备

public class RadomFile {

    public static void main(String[] args) {
        String source = "E:/JavaIOTest.txt";
        randomReader(source,20);
//        randomWrite(source);
        randomInsert(source, 7, "刘宇鹏");
//        write(source);
    }

    /**
     * 读取任意位置的数据
     * seek()
     *
     * @param path
     * @param postion
     */
    public static void randomReader(String path, int postion) {
        try {
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            //获取RandomAccessFile对象文件指针的位置，初始位置是0
            System.out.println("RandomAccessFile文件指针的初始位置:" + raf.getFilePointer());
            raf.seek(postion);//移动文件指针位置

            byte[] buff = new byte[1024];
            //用于保存实际读取的字节数
            int hasRead = 0;
            //循环读取 读入buffer中
            while ((hasRead = raf.read(buff)) > 0) {
                //打印读取的内容,并将字节转为字符串输入
                System.out.println(new String(buff, 0, hasRead));
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
            // raf.seek(4);//移动文件指针位置
            raf.write("我是追加的 \r\n".getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 任意位置插入数据
     */
    public static void randomInsert(String path, long points, String insertContent) {
        try {
            File tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();// 在JVM退出时删除

            RandomAccessFile raf = new RandomAccessFile(path, "rw");
            // 创建一个临时文件夹来保存插入点后的数据
            //FileOutputStream tmpOut = new FileOutputStream(tmp);
            BufferedReader in = new BufferedReader(new FileReader(path));
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            //FileInputStream tmpIn = new FileInputStream(tmp);
            raf.seek(points);
            /** 将插入点后的内容读入临时文件夹 **/

            //byte[] buff = new byte[1024];
            String line = "";
            // 用于保存临时读取的字节数
            int hasRead = 0;
            // 循环读取插入点后的内容
//            while ((hasRead = in.read(line)) > 0) {
//                // 将读取的数据写入临时文件中
////                out.write(buff, 0, hasRead);
//                out.write(line);
//            }
//
//            // 插入需要指定添加的数据
//            raf.seek(points);// 返回原来的插入处
//            // 追加需要追加的内容
//            raf.writeUTF(insertContent);
//            // 最后追加临时文件中的内容
//            while ((hasRead = in.read(line)) > 0) {
//                raf.writeBytes(line);
////                raf.write(buff, 0, hasRead);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write(String pathname) {
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = null; // 建立一个输入流对象reader
        OutputStreamWriter writer = null;
        try {
//            reader = new InputStreamReader(
//                    new FileInputStream(filename));
//            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
//            writer = new OutputStreamWriter(new FileOutputStream(filename));
//            BufferedWriter wr = new BufferedWriter(writer);
//            wr.write("djkasjdkalskdjkasjdksajdkajkdsakdddddddddddddddddddddkkkkkkwww222dsskn");
//
//            String line = "";
//            line = br.readLine();
//
//            while (line != null) {
//                line = br.readLine(); // 一次读入一行数据
//            }


            /* 写入Txt文件 */
            File writename = new File(pathname); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("我会写入文件啦\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
