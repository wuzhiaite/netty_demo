package com.wuzhiaite.netty_demo.start01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 *
 */
@ChannelHandler.Sharable
public class EcoServer {

    private Integer port;

    public EcoServer(Integer port){
        this.port = port ;
    }

    public void start() throws Exception{
        final EcoServerHandler serverHandler = new EcoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
                ChannelFuture f = b.bind().sync();//异步绑定服务器
                f.channel().closeFuture().sync();//获取Channel的closeFuture阻塞直到完成
        }  finally {
            group.shutdownGracefully().sync();
        }


    }

    public static void main(String[] args) throws Exception {
//        if(args.length != 1){
//            System.out.println("Usage:"+EcoServer.class.getSimpleName()+"<port>");
//        }
//        int port = Integer.parseInt(args[0]);
        int port = 8008;
        new EcoServer(port).start();

    }





}
