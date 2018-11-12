package AIOSocketTest.ServerHandler;

import AIOSocketTest.Server.ServerAio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        //继续接受其他客户端的请求
        ServerAio.clientCount++;
        System.out.println("连接的客户端数：" + ServerAio.clientCount);
        serverHandler.channel.accept(serverHandler, this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //建立连接之后当然要先看是否有客户端有什请求
        //异步读  第三个参数为接收消息回调的业务Handler
        channel.read(buffer, buffer, new ReadHandler(channel));
    }
    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
