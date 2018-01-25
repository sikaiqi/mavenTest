package cn.skq.test.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by admin on 2017/10/13.
 */
public class ServerSocketHandler implements Runnable{

    private Socket socket;

    public ServerSocketHandler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        InputStreamReader in = null;
        OutputStreamWriter out = null;
        try {
            in = new InputStreamReader(socket.getInputStream());
            out = new OutputStreamWriter(socket.getOutputStream());

            char[] b =new char[1024];
            int len = 0;
            int s = in.read(b);
            System.out.println("ss"+s);
            while ((len = in.read(b))!=-1){
//                System.out.println("111");
//                String str = new String(b);
//                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
}
