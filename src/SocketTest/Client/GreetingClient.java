package socketTest.Client;

import java.net.*;
import java.io.*;

public class GreetingClient
{
    public static void main(String [] args)
    {
        //String serverName = args[0];
        String serverName = "localhost";
        //int port = Integer.parseInt(args[1]);
        int port = 5600;
        try
        {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            BufferedInputStream in = new BufferedInputStream(inFromServer);
            byte[] b = new byte[1024];
            in.read(b);
            System.out.println("服务器响应： " + new String(b));
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}