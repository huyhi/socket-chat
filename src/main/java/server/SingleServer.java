package server;

import asyncJob.ServerListenThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SingleServer {
    public int port;
    public ServerSocket serverSocket;
    public Socket socket;
    public OutputStream outputStream;

    public SingleServer(int port) {
        this.port = port;
    }

    public void sendMsg(String msg) throws IOException {
        try {
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void runServer() throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("socket chat server start at port: " + port);

        while (true) {
            this.socket = serverSocket.accept();
            //thread will be blocked until a request arrive
            System.out.println("accept request from " + socket.getInetAddress().getHostAddress());

            //start the thread to listen client server and print out
            new Thread(new ServerListenThread(socket)).start();

            this.outputStream = socket.getOutputStream();
            Scanner stdInReader = new Scanner(System.in);
            while (stdInReader.hasNext()) {
                sendMsg(stdInReader.next());
            }
        }
    }


    public static void main(String[] args) {
        SingleServer singleServer = new SingleServer(6666);

        try {
            singleServer.runServer();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
