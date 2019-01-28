package asyncJob;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerListenThread implements Runnable{
    private Socket socket;
    private InputStream inputStream;

    public ServerListenThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
    }

    @Override
    public void run() {
        while (true) {
            try {
//                int first = this.inputStream.read();
//                if (first == -1) {
//                    throw new RuntimeException("disconnected.");
//                }
//                int second = this.inputStream.read();
//                int msgLen = (first<<8) + second;
//                byte[] readBuffer = new byte[msgLen];
//                this.inputStream.read(readBuffer);
//                System.out.println(socket.getInetAddress().getHostAddress() + ">\n" + new String(readBuffer,"UTF-8"));

//                int msgLen;
//                byte[] readBuf = new byte[1024];
//                StringBuilder stringBuilder = new StringBuilder();
//                while ((msgLen = this.inputStream.read(readBuf)) != -1) {
//                    stringBuilder.append(new String(readBuf, 0, msgLen, "UTF-8"));
//                }
//                System.out.println(socket.getInetAddress().getHostAddress() + ">" + stringBuilder.toString());

                byte[] readBuf = new byte[1024];
                this.inputStream.read(readBuf);
                System.out.println(socket.getInetAddress().getHostAddress() + " >" + new String(readBuf, "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
