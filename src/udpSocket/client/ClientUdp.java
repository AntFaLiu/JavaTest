package udpSocket.client;

import java.io.IOException;
import java.net.*;


public class ClientUdp {
    private final int PORT = 5060;
    private String sendStr = "hello";
    private String netAddress = "127.0.0.1";
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public ClientUdp() {
        try {
            datagramSocket = new DatagramSocket();
            byte[] buf = sendStr.getBytes();
            InetAddress address = InetAddress.getByName(netAddress);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT);
            datagramSocket.send(datagramPacket);
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);
            String receStr = new String(recePacket.getData(), 0, recePacket.getLength());

            System.out.println("receStr: " + receStr);
            //获取服务端ip
            InetAddress serverIp = recePacket.getAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ClientUdp clientUdp = new ClientUdp();
                }
            }).start();
        }
    }
}
