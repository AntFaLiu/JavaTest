package jvm;

import java.net.Socket;

//这个例子就证明了启动类加载器的作用
public class Test {
    public static void main(String[] args) {
        String str = "lyp";
        System.out.println("String 类的加载器是:    "+str.getClass().getClassLoader());
        Socket s = new Socket();
        System.out.println("Socket 类的加载器是:    "+s.getClass().getClassLoader());

    }
}
