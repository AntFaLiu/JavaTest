package udpSocket.server;

public class ServerUdpTest {

    public static void main(String[] args) {
        new Thread(new ServerUdp()).start();
    }
}
