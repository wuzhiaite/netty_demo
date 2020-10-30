package com.wuzhiaite.netty_demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringTest {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;

        while(true){
            int byteRead = 0;
            while( byteRead < messageLength ){
                long l = socketChannel.read(byteBuffers);
                byteRead += l ;//累计读取的字节数
                System.out.println("byteRead="+byteRead);
                //使用流打印，当前的buffer的position和limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "positon="+
                        buffer.position()+",limit="+buffer.limit())
                        .forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
            //将数据回显到客户端
            long byteWrite = 0 ;
            while(byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            //将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.clear();
            });
            System.out.println("byteRead="+byteRead+";byteWrite="+byteWrite);

        }

    }

}
