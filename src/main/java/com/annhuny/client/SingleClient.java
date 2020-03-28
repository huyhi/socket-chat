package com.annhuny.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class SingleClient {
    private Socket socket;
    private String host;
    private InputStream in;
    private OutputStream out;
    private int port;

    public SingleClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(host, port);
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        System.out.println("connect to " + host + ":" + port);
    }

    public void sendMsg(String msg) throws IOException {
        out.write(msg.getBytes(), 0, msg.length());
    }

    public static void main(String[] args) {
        try {
            SingleClient client = new SingleClient("127.0.0.1", 9000);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                client.sendMsg(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
