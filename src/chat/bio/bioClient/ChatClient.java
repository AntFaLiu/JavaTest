package chat.bio.bioClient;

import chat.bio.hander.Receive;
import chat.bio.hander.Send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    public static void main(String[] args) throws UnknownHostException, IOException {

        System.out.println("请输入名称:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if (name.equals("")) {
            return;
        }

        Socket client = new Socket("localhost", 9999);
        //发送线程
        new Thread(new Send(client, name)).start();

        //接收服务器的响应的线程
        new Thread(new Receive(client)).start();
    }
}
