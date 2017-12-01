package cn.skq.test.bio;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by admin on 2017/10/16.
 */
public class Client {
    private static final int PORT = 6677;
    private static final String ADDRESS = "127.0.0.1";
    public static void main(String[] args) {
        InputStreamReader in = null;
        OutputStreamWriter out = null;
        try {
            Socket socket = new Socket(ADDRESS,PORT);
            in = new InputStreamReader(socket.getInputStream());
            out = new OutputStreamWriter(socket.getOutputStream());
            out.write("客户端发送数据");
//            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
        }


    }

}
