import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class Client {

    public static void main(String[] args) {
        String msg = "Hello, сервер!";
        new Client("localhost", 9000).send(msg, System.out::println);
    }

    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void send(String msg, Consumer<String> callback) {
        new Thread(() -> {
            try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host, port))) {

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                byteBuffer.putInt(msg.getBytes(StandardCharsets.UTF_8).length);
                byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));
                byteBuffer.flip();


                while (byteBuffer.hasRemaining()) {
                    socketChannel.write(byteBuffer);
                }
                byteBuffer.clear();
                byte[] data;
                while (byteBuffer.position() < 4) {
                    if (socketChannel.read(byteBuffer) == -1) {
                        callback.accept("Interrupted");
                        return;
                    }
                }
                byteBuffer.flip();
                data = new byte[byteBuffer.getInt()];
                byteBuffer.compact();
                while (byteBuffer.position() < data.length) {
                    if (socketChannel.read(byteBuffer) == -1) {
                        callback.accept("Interrupted");
                        return;
                    }
                }
                byteBuffer.flip();
                byteBuffer.get(data);
                callback.accept("Server answered: " + new String(data, StandardCharsets.UTF_8));
                System.out.println("Connection closed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }




}
