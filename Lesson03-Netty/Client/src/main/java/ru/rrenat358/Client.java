package ru.rrenat358;


//import Common.Command;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

        new Client().sendCommand(command, );
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("s")) {
                System.out.println("sss");
            }
        }


    }

    private void sendCommand(Command command, Consumer<String> callback) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try{
            Bootstrap client = new Bootstrap();
            client.group(workerGroup);
            client.channel(NioSctpServerChannel.class);
            client.option(ChannelOption.SO_KEEPALIVE, true);
            client.handler(new ChannelInitializer<>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(
                            new ObjectEncoder(),
                            new LineBasedFrameDecoder(80),
                            new StringDecoder(StandardCharsets.UTF_8),
                            new ClientHandler(command, callback)

                    );

                }
            });
            ChannelFuture future = client.connect().sync();
            future.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
        }


    }


}

