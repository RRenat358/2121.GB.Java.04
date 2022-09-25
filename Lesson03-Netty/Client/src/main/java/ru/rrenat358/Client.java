package ru.rrenat358;


import Common.Command;
import io.netty.channel.EventLoopGroup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.function.Consumer;

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

        new Client().sendCommand();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("s")) {
                System.out.println("sss");
            }
        }


    }

    private void sendCommand(Command command, Consumer<String> callback) {
        EventLoopGroup workerGroup =



    }


}

