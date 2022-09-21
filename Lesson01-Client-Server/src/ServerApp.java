import java.util.Scanner;

public class ServerApp {

    public static void main(String[] args) {

        Server01 server = new Server01(8358);
        new Thread(server).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.nextLine().equals("stop")) {
            server.stop();
        }




    }

}
