package client;

import terminal.dialogTerminal;

import java.io.IOException;
import java.net.Socket;

public class SingleClient {
    private String host;
    private int port;
    private Socket socket;

    public SingleClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connectToServer() throws IOException {
        this.socket = new Socket(host, port);
        System.out.println("success connect to " + host + ":" + port);
        //开始聊天框
        new dialogTerminal(socket).open();
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
