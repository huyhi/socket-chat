package server;

import terminal.dialogTerminal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleServer {
    public int port;
    public ServerSocket serverSocket;
    public Socket socket;

    public SingleServer(int port) {
        this.port = port;
    }


    public void runServer() throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("socket chat server start at port: " + port);

        while (true) {
            this.socket = serverSocket.accept();
            //thread will be blocked until a request arrive
            System.out.println("accept request from " + socket.getInetAddress().getHostAddress());

            //开始聊天框
            new dialogTerminal(socket).open();
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
