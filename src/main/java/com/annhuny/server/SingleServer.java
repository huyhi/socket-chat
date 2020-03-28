package com.annhuny.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleServer {
    private int port;
    private ServerSocket serverSocket;

    public SingleServer(int port) {
        this.port = port;
    }


    private void runServer() throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("server start at port: " + port);

        Executor services = Executors.newCachedThreadPool();
        while (true) {
            //thread will be blocked until a request arrive
            Socket socket = serverSocket.accept();
            System.out.println("accept request from " + socket.getInetAddress().getHostAddress());
            services.execute(new WorkerThread(socket));
        }
    }


    public static void main(String[] args) {
        SingleServer singleServer = new SingleServer(9000);

        try {
            singleServer.runServer();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
