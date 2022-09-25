package ru.rrenat358;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Command> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command command) throws Exception {
        System.out.println("command = " + command);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ServerHandler exception");
        cause.printStackTrace();
    }
}
