package ru.rrenat358;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class Server {

    private static final String HOST = "localhost";
    private static final int PORT = 13581;
    private static final String clientDataUserPath = "Client/DataUser/";

    private static final int MAX_OBJECT_SIZE = 1024 * 1024 * 100;


    private String host;
    private int port;

    public static void main(String[] args) {
        new Server(HOST, PORT).startServer();
    }

    public Server() {
    }

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void startServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup);
            server.channel(NioSctpServerChannel.class);
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.childOption(ChannelOption.SO_KEEPALIVE, true);
            server.childHandler(new ChannelInitializer<>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(
                            new StringEncoder(StandardCharsets, UTF_8),
                            new ObjectDecoder(MAX_OBJECT_SIZE, ClassResolvers.cacheDisabled(null)),
                            new ServerHandler()


                    );

                }
            });

        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }

}
