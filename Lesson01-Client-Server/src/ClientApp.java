import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientApp {

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp("localhost", 8358);
        String response = clientApp.send("Hello, Server!");
        System.out.println("response = " + response);

    }


    private final String host;
    private final int port;

    public ClientApp(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String send(String msg) {
        try (Socket socket = new Socket(host, port)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] data = msg.getBytes(StandardCharsets.UTF_8);

            DataOutput dataOutput = new DataOutputStream(outputStream);
            dataOutput.writeInt(data.length);

            outputStream.write(data);
            System.out.println("Message sent!");

            DataInput dataInput = new DataInputStream(inputStream);
            int size = dataInput.readInt();
            byte[] bytes = new byte[size];
            dataInput.readFully(bytes);

            return new String(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }






}
