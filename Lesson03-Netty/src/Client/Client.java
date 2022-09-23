package Client;


import Common.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 13581;
    private static final String clientDataUserPath = "Client/DataUser/";


    private final String host;
    private final int port;

    public Client() {
        this(HOST, PORT);
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws IOException {

        File file = new File(clientDataUserPath + "my-file.txt");

        Command command = new Command("put", file, Files.readAllBytes(file.toPath()));



    }



}

