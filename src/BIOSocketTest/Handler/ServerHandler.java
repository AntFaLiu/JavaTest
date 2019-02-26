package bioSocketTest.handler;


import java.io.*;
import java.net.Socket;

public class ServerHandler implements Runnable {

    //定义一个变量用来保存  socket  ------》  主线程监听到的   子线程有请求的一个socket
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * 在run方法中写具体需要做的事情，定义这个IO流和client之间通信 这些东西用完之后要关闭
     */
    @Override
    public void run() {

        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String expression;
            String result;
            while (true) {
                out.flush();
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null,退出循环
                //如果得到非空值，就尝试计算结果并返回
                if ((expression = in.readLine()) == null) break;
                System.out.println("服务器收到消息：" + expression);
                try {
                    result = String.valueOf(expression.hashCode());
                } catch (Exception e) {
                    result = "计算错误：" + e.getMessage();
                }
                out.flush();
                out.write(result + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一些必要的清理工作
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
