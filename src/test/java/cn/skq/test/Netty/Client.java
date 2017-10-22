package cn.skq.test.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2017/10/22 0022.
 */
public class Client {

    public static void main(String[] args) throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new ClientHandler());
            }
        });

        ChannelFuture  cf1 = b.connect("127.0.0.1",6677).sync();

        //使用netty的辅助工具类Unpooled将字节数据转成buffer,write 方法不会直接将消息发送出去，而是写入缓冲区，由flush方法真正发
        /*cf1.channel().write(Unpooled.copiedBuffer("777".getBytes()));
        cf1.channel().flush();*/

        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));

        cf1.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
