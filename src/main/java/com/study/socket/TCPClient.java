package com.study.socket;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        //创建 socket，并指定连接的是本机的端口号为65000的服务器 socket
        Socket socket = new Socket("127.0.0.1", 65000);
        //获取输出流
        OutputStream os = socket.getOutputStream();
        //获取输入流
        InputStream is = socket.getInputStream();
        //将要传递给 server的字符串参数转换成byte数组，并数组写入到输出流中
        os.write(new String("hello world").getBytes());
        int ch = 0;
        byte[] buff = new byte[1024];
        ch = is.read(buff);
        String content = new String(buff, 0, ch);
        System.out.println(content);
        os.close();
        is.close();
        socket.close();
    }
}
