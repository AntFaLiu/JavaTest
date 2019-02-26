import java.io.*;
import java.util.Scanner;

//public class IoTest {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//        do {
//            str = br.readLine();
//            System.out.println(str);
//        } while (!str.equals("end"));

        // 两种创建文件的方式
//        OutputStream out = new FileOutputStream("Users/ant_oliu/javaTest.txt");
//        File file = new File("Users/ant_oliu/Documents/javaTest.txt");
//        OutputStream out = new FileOutputStream(file);
//        out.write(1);
//        InputStream f = new FileInputStream("Users/ant_oliu/Documents/javaTest.txt");
//        //InputStream out = new FileInputStream(f);
//        int i = f.read();
//        System.out.println(i);
//
//        Scanner scan = new Scanner(System.in);
//        File file = new File("Users/ant_oliu");
//        String s = file.getParent();
//        System.out.println("parent: " + s);
//        System.out.println("list: " + file.listFiles());
//    }
//}

//public class IoTest {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////        String str;
////        do {
////            str = br.readLine();
////            System.out.println(str);
////        } while (!str.equals("end"));
//
//        // 两种创建文件的方式
////        OutputStream out = new FileOutputStream("Users/ant_oliu/javaTest.txt");
////        File file = new File("Users/ant_oliu/Documents/javaTest.txt");
////        OutputStream out = new FileOutputStream(file);
////        out.write(1);
////        InputStream f = new FileInputStream("Users/ant_oliu/Documents/javaTest.txt");
////        //InputStream out = new FileInputStream(f);
////        int i = f.read();
////        System.out.println(i);
////
////        Scanner scan = new Scanner(System.in);
//        File file = new File("Users/ant_oliu");
//        String s = file.getParent();
//        System.out.println("parent: " + s);
//        System.out.println("list: " + file.listFiles());
//    }
//}


public class IoTest {

    public static String readTxtFile(String FileName) throws Exception {
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream memStream = null;
        byte[] data = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(FileName));
            memStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                memStream.write(buffer, 0, len);
            }
            data = memStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (memStream != null) {
                    memStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }

        String s = new String(data);
        if (s != null) {
            bufferedWriter(s, "/Users/ant_oliu/Documents/资料/党员个人信息情况表.doc");
        }
        return new String(data);
    }


    /**
     *      * 以行为单位读写文件内容 
     *      * @param filePath 
     */


    public static String readTxtFileJson(String filePath) throws Exception {
        File file = new File(filePath);
        InputStreamReader read = null;
        StringBuffer sb = null;
        try {
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                sb = new StringBuffer();
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }

        if (sb != null) {
            bufferedWriter(sb.toString(), "/Users/ant_oliu/Documents/资料/党员个人信息情况表.doc");
        }
        return sb != null ? sb.toString() : null; // GsonUtil.transJsonStrToObject(sb.toString(), KubeData.class)
    }

    /**
     *      * 缓冲字符写入文件，写字符串，数组或字符数据
     *      * @param content
     *      * @throws exception
     *      
     */


    public static void bufferedWriter(String content, String filePath) throws Exception {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(new File(filePath).getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *      * 文件输出流,必须将数据转换为字节，并保存到文件
     *      * @param content
     *      * @throws exception
     *      
     */

    public static void fileOutputStream(String content, String filePath) throws Exception {
        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(new File(filePath));
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *      * 测试1：
     *      * 文件大小：2m
     *      * 读取:readTxtFileJson 行读，写入：bufferedWriter 缓冲字符写入
     *      * 用时：51秒
     *      * 
     *      * 读取:readTxtFile 缓冲读取，写入：bufferedWriter 缓冲字符写入
     *      * 用时：31秒
     *      * 
     *      * 测试2：
     *      * 文件大小：10m
     *      * 读取:readTxtFileJson 行读，写入：fileOutputStream 文件输出流写入
     *      * 用时：501秒
     *      * 
     *      * 读取:readTxtFile 缓冲读取，写入：fileOutputStream 文件输出流写入
     *      * 用时：172秒
     *      * 
     *      * 文件大小：10m
     *      * 读取:readTxtFileJson 行读，写入：bufferedWriter 缓冲字符写入
     *      * 用时：293秒
     *      * 
     *      * 读取:readTxtFile 缓冲读取，写入：bufferedWriter 缓冲字符写入
     *      * 用时：132秒
     *      * 
     *      * 总结：
     *      * 不按格式读取效率高写入文件后大小比源文件小：readTxtFile 缓冲读取，bufferedWriter 缓冲字符写入
     *      * 按格式读取效率偏低(是第一种方式的一倍左右)写入文件后大小比源文件大小相当：readTxtFileJson 行读 ，bufferedWriter 缓冲字符写入
     *      * @param args
     *      
     */
    public static void main(String[] args) {
        try {
            long date1 = System.currentTimeMillis();
            String s = readTxtFileJson("/Users/ant_oliu/Documents/资料/党员个人信息情况表.doc");
//System.out.println(s);
            System.out.println(System.currentTimeMillis() - date1);

// if(Util.isNotNull(kubeData)){
// System.out.println(kubeData.getKind()+"=="+kubeData.getApiVersion());
// }

            long date2 = System.currentTimeMillis();
            String s1 = readTxtFile("/Users/ant_oliu/Documents/资料/党员个人信息情况表.doc");
//System.out.println(s1);
            System.out.println(System.currentTimeMillis() - date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//File流、Object流、Buffer流比较
//FileInputStream／FileOutputStream用于从二进制文件中读取/写入字节数据
//
//ObjectInputStream/ObjectOutputStream用于对象的输入，输出字节数据（对象需要序列化）
//
//BufferedInputStreamTest/BufferedOutputStream实现缓冲的输入/输出字节数据
//
//操作耗时比

