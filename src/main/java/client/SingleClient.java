package client;

import asyncJob.ServerListenThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

public class SingleClient {
    private String host;
    private int port;
    private Socket socket;
    private OutputStream outputStream;

    public SingleClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendMsg(String msg) throws IOException {
        try {
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void connectToServer() throws IOException {
        this.socket = new Socket(host, port);
        System.out.println("success connect to " + host + ":" + port);

        new Thread(new ServerListenThread(socket)).start();

        this.outputStream = socket.getOutputStream();
        Scanner stdInReader = new Scanner(System.in);
        while (stdInReader.hasNext()) {
            sendMsg(stdInReader.next());
        }
    }


    public static void main(String[] args) {
        SingleClient singleClient = new SingleClient("127.0.0.1", 6666);

        try {
            singleClient.connectToServer();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
