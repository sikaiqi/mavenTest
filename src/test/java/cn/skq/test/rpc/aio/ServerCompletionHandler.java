package cn.skq.test.rpc.aio;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //TODO
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        //TODO
    }
}
