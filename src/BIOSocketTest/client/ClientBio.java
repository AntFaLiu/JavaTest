package bioSocketTest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//定义服务器名 和端口号    流 和服务器之间通信
public class ClientBio {

    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        boolean falg = false;
        while (!falg) {
            System.out.println("请输入；");
            String expression = scn.next();
            send(expression);
            while (true){
                System.out.println("继续计算？(Y/N)");
                String str = scn.next();
                if (expression.equalsIgnoreCase("N")) {
                    falg = true;
                    break;
                }else if (str.equalsIgnoreCase("Y")){
                    System.out.println("请输入：  ");
                    expression =  scn.next();
                    send(expression);
                }
            }
        }
    }

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public static void send(int port, String expression) {
        System.out.println("输入：" + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);  //自动刷新
            out.println(expression);
            System.out.println("___结果为：" + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //必要的清理工作
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
