import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;

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
