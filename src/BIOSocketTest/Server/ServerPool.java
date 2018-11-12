package BIOSocketTest.Server;

//伪异步IO模型   类似BIO模型 只是用线程池来管理工作线程  通过线程池的机制减少了一部分资源的浪费


import BIOSocketTest.Handler.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerPool {

    //默认的端口号
    private static int DEFAULT_PORT = 12345;
    //单例的ServerSocket
    private static ServerSocket server;
    //懒汉的单例模式
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);

    public static void main(String[] args) {
        try {
            start(DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void start(int port) throws IOException {
        if (server != null) return;
        try {
            //通过构造函数创建ServerSocket
            //如果端口合法且空闲，服务端就监听成功
            server = new ServerSocket(port);
            System.out.println("服务器已启动，端口号：" + port);
            Socket socket;
            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accept操作上。
            while (true) {
                socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //然后创建一个新的线程处理这条Socket链路
                executorService.execute(new ServerHandler(socket));
            }
        } finally {
            //一些必要的清理工作
            if (server != null) {
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }
}
