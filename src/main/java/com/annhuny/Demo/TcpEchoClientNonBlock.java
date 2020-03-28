package com.annhuny.Demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TcpEchoClientNonBlock {

    public static void main(String[] args) throws IOException, InterruptedException {
        String server = "127.0.0.1";
        int port = 9000;

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        if (!socketChannel.connect(new InetSocketAddress(server, port))) {
            while (!socketChannel.finishConnect()) {
                System.out.print(".");
                Thread.sleep(500);
            }
        }

        byte[] msg = "hello world".getBytes();
        ByteBuffer writeBuf = ByteBuffer.wrap(msg);
        ByteBuffer readBuf = ByteBuffer.allocate(msg.length);
        
        int totalByteReceived = 0, byteReceived;
        while (totalByteReceived < msg.length) {
            if (writeBuf.hasRemaining()) {
                socketChannel.write(writeBuf);
            }
            if ((byteReceived = socketChannel.read(readBuf)) == -1) {
                System.out.println("connection closed");
                return;
            }
            totalByteReceived += byteReceived;
        }

        System.out.println("Received: " + new String(readBuf.array(), 0, totalByteReceived));
        socketChannel.close();
    }
}
