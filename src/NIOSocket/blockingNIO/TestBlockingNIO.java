package nioSocket.blockingNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIO {
    public void client() throws IOException {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1", 9898));

        //此时文件全部都在FileChannel中
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\1.txt"),
                StandardOpenOption.READ);

        //2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3. 读取本地文件，并发送到服务端
        while(inChannel.read(buf) != -1){ //FileChannel中的文件读入缓冲区
            buf.flip();
            sChannel.write(buf); //将缓冲区里的数据写入到SocketChannel中
            buf.clear(); //清空缓冲区
        }
        //循环后，文件全部被送入了SocketChannel中
        //4. 关闭通道
        inChannel.close();
        sChannel.close();
    }

    //服务端
    public void server() throws IOException{
        //1. 获取通道，表示在本机开了一个ServerSocketChannel通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //表示在本机创建一个空文件，等待写入数据
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\2.txt"),
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);
        //2. 绑定端口
        ssChannel.bind(new InetSocketAddress(9898));

        //3. 获取客户端连接的通道，当与客户端建立好连接后，
        SocketChannel sChannel = ssChannel.accept();

        //4. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //5. 接收客户端的数据，并保存到本地
        while(sChannel.read(buf) != -1){
            try {
                Thread.sleep(5000);
            }catch (Exception e){

            }
            buf.flip();
            outChannel.write(buf); //将缓冲区里的数据写入文件
            buf.clear();
        }

        //6. 关闭通道
        sChannel.close();
        outChannel.close();
        ssChannel.close();

    }

    public static void main(String[] args) {
        new Thread(()->{
            try {
                new TestBlockingNIO().client();
                System.out.println("bioClient end....");
            }catch (Exception e){

            }

        }).start();
        new Thread(()->{
            try {
                new TestBlockingNIO().server();
                System.out.println("server end .....");
            }catch (Exception e){

            }
        }).start();
    }
}
