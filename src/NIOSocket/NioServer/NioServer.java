package nioSocket.NioServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

//NIO 指的就是Selector编程，了解Selector编程 首先要了解三个概念
// 1：Buffer、2:channe 和我们前面将的流是一个作用只不过流是单向的而channel是双向的
//Selector是Java  NIO 编程的基础。
// Selector提供选择已经就绪的任务的能力：Selector会不断轮询注册在其上的Channel，如果某个Channel上面发生读或者写事件，
// 这个Channel就处于就绪状态，会被Selector轮询出来，然后通过SelectionKey可以获取就绪Channel的集合，进行后续的I/O操作。
// 一个Selector可以同时轮询多个Channel，因为JDK使用了epoll()代替传统的select实现，所以没有最大连接句柄1024/2048的限制。
// 所以，只需要一个线程负责Selector的轮询，就可以接入成千上万的客户端。
public class NioServer {

    private final int port = 8787;
    private final int BLOCK_SIZE = 4096;
    private Selector selector;
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK_SIZE);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK_SIZE);

    //构造函数
    public NioServer() throws IOException {
        //打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //服务器配置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //获取与通道关联的 ServerSocket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        //绑定端口
        serverSocket.bind(new InetSocketAddress(port));
        //打开一个选择器
        selector = Selector.open();
        //注册到selector上，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server:init successfuly.");
    }

    public static void main(String[] args) {
        try {
            NioServer nioServer = new NioServer();
            nioServer.linstenr();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 监听端口
     */
    private void linstenr() throws Exception {

        while (true) {
            //选择一组键
            selector.select();
            //返回获取选择的键集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys.isEmpty()) {
                continue;
            }
            //遍历，循环处理请求的键集
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handlerKey(selectionKey);
            }
            Thread.sleep(4000);
        }

    }

    /**
     * 处理对应的  SelectionKey
     *
     * @param selectionKey
     */
    private void handlerKey(SelectionKey selectionKey) throws IOException {

        ServerSocketChannel server;
        SocketChannel client;

        // 测试此键的通道是否已准备好接受新的套接字连接
        if (selectionKey.isAcceptable()) {
            //此键对应的关联通道
            server = (ServerSocketChannel) selectionKey.channel();
            //接受到此通道套接字的连接
            client = server.accept();
            //配置为非阻塞
            client.configureBlocking(false);
            //注册到selector 等待连接
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            client = (SocketChannel) selectionKey.channel();
            //将缓冲区清空，下面读取
            receiveBuffer.clear();
            //将客户端发送来的数据读取到 buffer中
            int count = client.read(receiveBuffer);
            if (count > 0) {
                String receiveMessage = new String(receiveBuffer.array(), 0, count);
                System.out.println("server:接受客户端的数据:" + receiveMessage);
                client.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (selectionKey.isWritable()) {
            //发送消息buffer 清空
            sendBuffer.clear();
            //返回该键对应的通道
            client = (SocketChannel) selectionKey.channel();
            String sendMessage = "Send form server...Hello... " + new Random().nextInt(100) + " .";
            //向缓冲区中写入数据
            sendBuffer.put(sendMessage.getBytes());
            //put了数据，标志位被改变
            sendBuffer.flip();
            //数据输出到通道
            client.write(sendBuffer);
            System.out.println("server:服务器向客户端发送数据:" + sendMessage);
            client.register(selector, SelectionKey.OP_READ);
        }
    }
}
// 可以看到，创建NIO服务端的主要步骤如下：
//   打开ServerSocketChannel，监听客户端连接
//   绑定监听端口，设置连接为非阻塞模式
//   创建Reactor线程，创建多路复用器并启动线程
//   将ServerSocketChannel注册到Reactor线程中的Selector上，监听ACCEPT事件
//   Selector轮询准备就绪的key
//   Selector监听到新的客户端接入，处理新的接入请求，完成TCP三次握手，简历物理链路
//   设置客户端链路为非阻塞模式
//   将新接入的客户端连接注册到Reactor线程的Selector上，监听读操作，读取客户端发送的网络消息
//   异步读取客户端消息到缓冲区
//   对Buffer编解码，处理半包消息，将解码成功的消息封装成Task
//   将应答消息编码为Buffer，调用SocketChannel的write将消息异步发送给客户端
//   因为应答消息的发送，SocketChannel也是异步非阻塞的，所以不能保证一次能吧需要发送的数据发送完，此时就会出现写半包的问题。
//   我们需要注册写操作，不断轮询Selector将没有发送完的消息发送完毕，然后通过Buffer的hasRemain()方法判断消息是否发送完成。