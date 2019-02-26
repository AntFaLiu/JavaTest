package aioSocketTest.Server;

import aioSocketTest.ServerHandler.AsyncServerHandler;

public class ServerAio {
    private static int DEFAULT_PORT = 12345;
    private static AsyncServerHandler serverHandle;
    public volatile static long clientCount = 0;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null)
            return;
        serverHandle = new AsyncServerHandler(port);
        new Thread(serverHandle,"server").start();
    }
    public static void main(String[] args){
        ServerAio.start();
    }

}
