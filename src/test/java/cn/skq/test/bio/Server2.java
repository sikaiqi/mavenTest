package cn.skq.test.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/2/2 0002.
 */
public class Server2 {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();//创建线程池
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(6677);
            System.out.println("服务器启动！");
            while(true){
                Socket socket = ss.accept();
                System.out.println("来了一个新客户端！");

                //使用多线程解决传统BIO 下多客户端访问阻塞问题
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        hanle(socket);
                    }
                });
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
