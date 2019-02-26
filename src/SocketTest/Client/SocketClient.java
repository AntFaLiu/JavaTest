package socketTest.Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws UnknownHostException, IOException {

        int port = 7000;              //定义端口好
        String host = "localhost";   //定义host地址
        // 创建一个套接字并将其连接到指定端口号
        Socket socket = new Socket(host, port);

        DataInputStream dis = new DataInputStream(   //定义输入流
                new BufferedInputStream(socket.getInputStream()));
        DataOutputStream dos = new DataOutputStream(  //定义输出流
                new BufferedOutputStream(socket.getOutputStream()));
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            System.out.println("请输入正方形的边长:");
            double length = sc.nextDouble();
            dos.writeDouble(length);
            dos.flush();        //这个方法的作用是把缓冲区的数据强行输出
            double area = dis.readDouble();
            System.out.println("服务器返回的计算面积为:" + area);
            while (true) {
                System.out.println("继续计算？(Y/N)");
                String str = sc.next();
                if (str.equalsIgnoreCase("N")) {
                    dos.writeInt(0);
                    //dos.flush();
                    flag = true;
                    break;
                } else if (str.equalsIgnoreCase("Y")) {
                    dos.writeInt(1);
                    dos.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}
