package com.study.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioPlainEchoService {

    public static void improverService(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        while (true) {
            //阻塞知道收到新的客户端连接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from" + clientSocket);
            //将请求提交给线程池去执行
            executorService.execute(() ->{
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    //从客户端读取数据并原封不对会写回去
                    while (true) {
                        writer.println(reader.readLine());
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        improverService(65000);
    }
}
