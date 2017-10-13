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

    @Override
    public void run() {
        InputStreamReader in = null;
        OutputStreamWriter out = null;
        try {
            in = new InputStreamReader(socket.getInputStream());
            out = new OutputStreamWriter(socket.getOutputStream());

            while (in.read()!=-1){
                in.read();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
}
