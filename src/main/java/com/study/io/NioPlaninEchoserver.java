package com.study.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioPlaninEchoserver {

    public void serve(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        //将ServerSocket绑定到指定的端口里
        socket.bind(inetSocketAddress);
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        //将channel注册到Selector里，并说明让Selector关注的点，这里是关注建立连接这个事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                //阻塞等待就绪的channel，既没有与客户端建立连接前就一直轮询
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                //代码省略的部分是结合业务，正确处理异常的逻辑
                break;
            }
            //获取到Selector里所有就绪的SelectedKey实例，每将一个channel注册到一个selecor就会产生一个SelectionKey
            Set<SelectionKey> readKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //将就绪的SelectedKey才Selector中移除。因为马上就要去处理它，防止重复执行
                iterator.remove();
                try {
                    //若 SelectedKey处于 Acceptable状态
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        //接受客户端的连接
                        SocketChannel accept = server.accept();
                        System.out.println("Accepted connection from" + accept);
                        accept.configureBlocking(false);
                        //想selector注册soceketchannel,主要关注读写，并传入一个ByteBuffer实例攻读写缓存
                        accept.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, ByteBuffer.allocate(100));
                    }
                    //若SelectedKey处于 可读状态
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        //从channel里读取数据 存入ByteBuffer 里面
                        client.read(output);
                    }
                    //若SelectedKey处于 可写状态
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                        //将ByteBuffer里面的数据写入到channel里
                        client.write(output);
                        output.compact();
                    }
                } catch (IOException e) {
                    key.cancel();
                } finally {
                    key.channel().close();
                }


            }
        }
    }
}
