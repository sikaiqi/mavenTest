package cn.skq.test.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio 网络编程  缺点：同步阻塞
 *
 *
 */
public class Server {

    private static final int PORT = 6677;
    private static final String ADDRESS = "127.0.0.1";

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("启动Server端。。");
            Socket socket = ss.accept();
            System.out.println("Client端已连接。。");
            new Thread(new ServerSocketHandler(socket)).start();//启动线程处理客户端请求
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}