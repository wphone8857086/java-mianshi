package com.study.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(65000);
        while (true) {
            Socket s = socket.accept();
            new LengthCalculator(s).start();
        }

    }
}
