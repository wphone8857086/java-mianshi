package com.study.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioPlaninEchoServer {
    public void serve(int port) throws IOException {
        final AsynchronousServerSocketChannel socketChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        //将ServerSocket绑定到指定的端口里
        socketChannel.bind(inetSocketAddress);
        final CountDownLatch latch = new CountDownLatch(1);
        socketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(final AsynchronousSocketChannel channel, Object attachment) {
                //一旦完成处理，再次接受新的客户端请求
                socketChannel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(100);
                //在channel里植入一个读操作EchoCompletionHandler,一旦buffer有数据写入，在channel里植入一个读操作EchoCompletionHandler便会被唤醒
                channel.read(buffer, buffer, new EchoCompletionHandler(channel));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    // ingnore on close
                }

            }
        });
    }

    private final static class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
        private final AsynchronousSocketChannel channel;

        private EchoCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            //在channel里植入一个读操作EchoCompletionHandler,一旦buffer有数据写入，在channel里植入一个读操作EchoCompletionHandler便会被唤醒
            channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (buffer.hasRemaining()) {
                        //入股buffer里面还有内容，则再次出发写入操作将buffer里的内容写入channel
                        channel.write(buffer, buffer, this);
                    } else {
                        buffer.compact();
                        //如果channel里内容需要导入buffer里，则再次触发写入操作将channel里的内容读入到buffer
                        channel.read(buffer, buffer, EchoCompletionHandler.this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        // ingnore on close
                    }
                }
            });

        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (IOException e) {
                // ingnore on close
            }
        }
    }
}
