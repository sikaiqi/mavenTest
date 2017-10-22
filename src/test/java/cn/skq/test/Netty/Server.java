package cn.skq.test.Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2017/10/22 0022.
 */
public class Server {

    public static void main(String[] args) throws Exception {
        //1 第一个线程组是用于接收客户端连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2 第二个线程组是用于处理实际业务的
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //3 创建一个辅助启动类，对server端进行一系列的配置
        ServerBootstrap b = new ServerBootstrap();
        // 把2个线程组加入进来
        b.group(bossGroup,workerGroup)
            //指定使用NioServerSocketChannel 这种类型的通道
            .channel(NioServerSocketChannel.class)
            //一定要使用childHandler去绑定具体的事件处理器
            .childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(new ServerHandler());
                }
            })

            /**
             * 128 配置解释：
             * 服务器端TCP 内核模块维护有2个队列，我们暂时成为A ,B
             * 客户端想服务器端connect的时候，会发送带有SYN标志的包（第一次握手）
             * 服务器收到客户端发来的SYN 时，想客户端发送SYN ACK 确认（第二次握手）
             * 此时TCP 内核模块吧客户端连接加入到A队列中，然后服务器收到客户端发来的ACK 时（第三次握手）
             * TCP 内核模块吧客户端连接从A队列移到B队列，连接完成， 应用程序的accept 会返回
             * 也就是说accept方法会从B队列中取出完成3次握手的socket连接
             * A队列和B队列的长度之和就是backlog 。 当A,B 队列的长度之和大于backlog时，新连接将会被TCP 内核拒绝
             * 所以，如果backlog过小，可能会出现accept 速度跟不上，A,B 队列满了，导致新的客户端无法连接
             * 要注意的是，backlog 对程序支持的连接数并无影响，backlog影响的只是还没有被accept取出的连接
             * */

            //设置tcp 缓冲区
            .option(ChannelOption.SO_BACKLOG, 128)
            //保持连接
            .childOption(ChannelOption.SO_KEEPALIVE, true);

        //绑定制定的端口进行监听
        ChannelFuture f = b.bind(6677).sync();

        // Wait until the server socket is closed.
        f.channel().closeFuture().sync();

        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
