package terminal;

import asyncJob.ServerListenThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

public class dialogTerminal {
    //server端或者client端维护的socket
    private Socket socket;
    private OutputStream outputStream;

    public dialogTerminal(Socket socket) {
        this.socket = socket;
    }

    private void sendMsg(String msg) throws IOException {
        try {
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void open() throws IOException {
        new Thread(new ServerListenThread(socket)).start();
        this.outputStream = socket.getOutputStream();

        Scanner stdInReader = new Scanner(System.in);

        System.out.println("type 'exit' to close dialog");
        System.out.print("% >");

        while (stdInReader.hasNextLine()) {
            System.out.print("% >");
            sendMsg(stdInReader.nextLine());
        }
    }
}
