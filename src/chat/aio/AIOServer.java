package chat.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOServer {
    static final int PORT = 7781;
    final static String UTF_8 = "UTF-8";
    static List<AsynchronousSocketChannel> channelList
            = new ArrayList<AsynchronousSocketChannel>();

    public static void main(String[] args) throws Exception {
        AIOServer server = new AIOServer();
        Thread.sleep(20000);
        server.startListen();
    }

    public void startListen() throws InterruptedException, Exception {
/**
 * 创建一个线程池
 * 线程池负责两个任务：处理IO事件和触发CompletionHandler
 *
 */
        ExecutorService executor = Executors.newFixedThreadPool(20);
//以指定线程池来创建一个AsynchronousChannelGroup
//AsynchronousChannelGroup是异步channel的分组管理器
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup
                .withThreadPool(executor);
//以指定线程池来创建一个AsynchronousServerSocketChannel
        AsynchronousServerSocketChannel serverChannel
                = AsynchronousServerSocketChannel.open(channelGroup)
//指定监听本机的端口
                .bind(new InetSocketAddress(PORT));
/**
 * 使用CompletionHandler接受来自客户端的连接请求
 * 异步IO不知道accept()方法什么时候会接收到客户端的请求
 * 因此提供两个版本的accept来解决此问题
 * Future<AsynchronousSocketChannel> accept()
 * <A> void accept(A attachment, CompletionHandler<AsynchronousSocketChannel,
 * ? super A> handler);
 *
 */
        serverChannel.accept(null, new AcceptHandler(serverChannel));
    }
}

//实现自己的CompletionHandler类
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
    //定义一个ByteBuffer准备读取数据
    ByteBuffer buff = ByteBuffer.allocate(1024);
    private AsynchronousServerSocketChannel serverChannel;

    public AcceptHandler(AsynchronousServerSocketChannel sc) {
        this.serverChannel = sc;
    }

    //当实际IO操作完成时触发该方法
    @Override
    public void completed(final AsynchronousSocketChannel sc, Object attachment) {
//记录新连接进来的Channel
        AIOServer.channelList.add(sc);
//准备接受客户端的下一次连接
        serverChannel.accept(null, this);
        sc.read(buff, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
//将buff中的内容装换为字符串
                String content = StandardCharsets.UTF_8
                        .decode(buff).toString();
//遍历每个Channel，将收到的信息写入各Channel中
                for (AsynchronousSocketChannel c : AIOServer.channelList) {
                    try {
                        c.write(ByteBuffer.wrap(content.getBytes(
                                AIOServer.UTF_8))).get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                buff.clear();
//读取下一次数据
                sc.read(buff, null, this);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败：" + exc);
//从该Channel中读取数据失败，就将该Channel删除
                AIOServer.channelList.remove(sc);
            }
        });
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println("连接失败：" + exc);
    }
}
