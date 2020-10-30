package com.wuzhiaite.netty_demo.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {

    /**
     * 1.数据写入byte'buffer
     * 2.bytebuffer 再通过channel写入到文件
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {

        String str = "hello,尚硅谷";
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer bytebuffer = ByteBuffer.allocate(1024);
        bytebuffer.put(str.getBytes());
        bytebuffer.flip();
        //把bytebuffer写入到channel
        fileChannel.write(bytebuffer);
        fileOutputStream.close();
    }

}
