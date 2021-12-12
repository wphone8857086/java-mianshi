package com.study.socket;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LengthCalculator extends Thread{
    private Socket socket;
    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }
    @SneakyThrows
    @Override
    public void run(){
        try {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            int ch = 0;
            byte[] buff = new byte[1024];
            ch = is.read(buff);
            String content = new String(buff, 0, ch);
            System.out.println(content);
            os.write(String.valueOf(content.length()).getBytes());
            os.close();
            is.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
