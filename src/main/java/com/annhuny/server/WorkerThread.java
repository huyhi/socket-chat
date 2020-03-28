package com.annhuny.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerThread implements Runnable{
    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                byte[] recvBuf = new byte[65536];
                int recvSize;
                recvSize = in.read(recvBuf);

                if (recvSize == -1) {
                    System.out.println(socket.getInetAddress() + " 退出");
                    return;
                } else {
                    System.out.println("size:" + recvSize + " " + new String(recvBuf));
                    out.write(recvBuf, 0, recvSize);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
