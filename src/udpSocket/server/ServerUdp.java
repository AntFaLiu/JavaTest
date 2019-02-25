package udpSocket.server;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ServerUdp implements Runnable {

    private final int MAX_LENGTH = 1024;
    private final int PORT = 5060;
    private DatagramSocket datagramSocket;
    // 用以接收数据报
    private DatagramPacket datagramPacket;

    public void run() {
        try {
            init();
            while (true) {
                try {
                    byte[] buffer = new byte[MAX_LENGTH];
                    datagramPacket = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(datagramPacket);
                    String receStr = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                    System.out.println("server Rece:" + receStr);
                    System.out.println("server Port:" + datagramPacket.getPort());
                    System.out.println("接收数据包" + receStr);
                    byte[] bt = "I receive the message".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(bt, bt.length, datagramPacket.getAddress(), datagramPacket.getPort());
                    datagramSocket.send(sendPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("udp线程出现异常：" + e.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化连接
     */
    public void init() {
        try {
            datagramSocket = new DatagramSocket(PORT);
            System.out.println("udp服务端已经启动！");
        } catch (Exception e) {
            datagramSocket = null;
            System.out.println("udp服务端启动失败！");
            e.printStackTrace();
        }
    }
}
