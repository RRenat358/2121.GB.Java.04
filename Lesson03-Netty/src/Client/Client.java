package Client;


import Common.Command;

import java.io.File;
import java.nio.file.Files;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 13581;
    private static final String clientDataUserPaht = "Client/DataUser/";


    private final String host;
    private final int port;

    public Client() {
        this(HOST, PORT);
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {

        File file = new File(clientDataUserPaht + "my-file.txt");
        Command command = new Command();



    }



}

