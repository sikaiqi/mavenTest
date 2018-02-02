package cn.skq.test.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/2/2 0002.
 */
public class Server2 {

    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(6677);
            System.out.println("服务器启动！");
            while(true){
                Socket socket = ss.accept();
                System.out.println("来了一个新客户端！");
                hanle(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ss){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void hanle(Socket socket) {
        InputStream in = null;
        try {
            in = socket.getInputStream();
            byte[] bytes = new byte[1024];
            while(true){
                int len = in.read(bytes);
                if (len!=-1) {
                    System.out.println(new String(bytes,0,len));
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
