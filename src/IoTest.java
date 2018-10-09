import java.io.*;
import java.util.Scanner;

public class IoTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        File file = new File("Users/ant_oliu");
        String s = file.getParent();
        System.out.println("parent: " + s);
        System.out.println("list: " + file.listFiles());
    }
}
