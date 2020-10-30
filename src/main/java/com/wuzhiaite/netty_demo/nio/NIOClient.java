package com.wuzhiaite.netty_demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Lenovo
 */
public class NIOClient {


    public static void main(String[] args) throws IOException {


        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器端ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8889);
        //连接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间。客户端不会阻塞，可以做其他工作。。。。。。。。");
            }
        }

        //。。。连接成功，发送信息
        String str = "hello,尚硅谷";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //将buffer数据写入channel
        socketChannel.write(buffer);
        System.in.read();

    }
}
