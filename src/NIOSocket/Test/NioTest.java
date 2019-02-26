package nioSocket.Test;

import nioSocket.NIOClient.ClientNio;
import nioSocket.NioServer.ServerNio;

import java.util.Scanner;

public class NioTest {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        //运行服务器
        ServerNio.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        ClientNio.start();
        while(ClientNio.sendMsg(new Scanner(System.in).nextLine()));
    }
}
