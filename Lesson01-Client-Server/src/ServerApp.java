import java.util.Scanner;

public class ServerApp {

    public static void main(String[] args) {

        Server server = new Server(8358);
        new Thread(server).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.nextLine().equals("stop")) {
            server.stop();
        }




    }

}
