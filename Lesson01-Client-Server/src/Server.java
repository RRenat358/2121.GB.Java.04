import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server implements Runnable {


    private final int port;
    private ServerSocket serverSocket;
    private boolean isStopped;


    public Server(int port) {
        this.port = port;
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open server socket!", e);
        }
    }


    private void process(Socket socket) throws IOException {
        DataInput inputStream = new DataInputStream(socket.getInputStream());
        DataOutput outputStream = new DataOutputStream(socket.getOutputStream());

        int size =inputStream.readInt();
        byte[] data = new byte[size];
        inputStream.readFully(data);

        System.out.println("Received: " + new String(data));

        String response = "Hello, Client";
        byte[] outByte = response.getBytes(StandardCharsets.UTF_8);
        outputStream.writeInt(outByte.length);
        outputStream.write(outByte);

        System.out.println("Responding done");


    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Socket socket;

        openServerSocket();
        System.out.println("Server started");

        while (!isStopped) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                if (isStopped) {
                    System.out.println("Server stopped");
                    return;
                }
                throw new RuntimeException(e);
            }

            try {
                process(socket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


    }

    public void stop() {
        isStopped = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}
