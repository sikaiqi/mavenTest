package cn.skq.test.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class Server {

    private ExecutorService executorService;// 创建线程池

    private AsynchronousChannelGroup threadGroup;//创建线程组

    private AsynchronousServerSocketChannel assc;//创建服务器通道

    public Server(int port){
        try {

            executorService  = Executors.newCachedThreadPool();
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            assc = AsynchronousServerSocketChannel.open(threadGroup);//通过线程组开启服务器通道
            assc.bind(new InetSocketAddress(port));
            System.out.println("Server is started,port:"+port);
            assc.accept(this,new ServerCompletionHandler());//阻塞
            Thread.sleep(Integer.MAX_VALUE);//一直阻塞

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(6677);

    }
}
