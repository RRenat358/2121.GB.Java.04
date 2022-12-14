package ru.rrenat358;


import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerHandler extends SimpleChannelInboundHandler<Command> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command command) throws Exception {
        System.out.println("command = " + command);
        if (command.getCommand().equals("put")) {

            Path root = Path.of("Lesson03-Netty/Server/DataUser");
            Files.createDirectories(root);
            Path filePath = root.resolve(command.getFile().getPath());
            System.out.println("Файл получен и будет сохранён: \n" + filePath);

            Files.createDirectories(filePath.getParent());
            try {
                Files.createFile(filePath);
            } catch (FileAlreadyExistsException ignored) {
                // do nothing
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Files.write(filePath, command.getData());
        }
        ChannelFuture channelFuture = channelHandlerContext.writeAndFlush(
                String.format("Server: Файл получен: \n%s", command.getFile().getName())

        );
        System.out.println("Файл сохранён: \n" + command.getFile().getName());
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ServerHandler exception");
        cause.printStackTrace();
    }


}
