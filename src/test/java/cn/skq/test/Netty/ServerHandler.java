package cn.skq.test.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by Administrator on 2017/10/22 0022.
 */
public class ServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ((ByteBuf)msg).release();
        try {
            ByteBuf buf = (ByteBuf) msg;
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            String request = new String(data, "utf-8");
            System.out.println("Server:"+request);

            //写给客户端
            String response ="我是反馈信息";
          /*  ctx.write(Unpooled.copiedBuffer("888".getBytes()));
            ctx.flush();*/
          ctx.writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
