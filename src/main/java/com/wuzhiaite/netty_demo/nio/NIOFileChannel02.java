package com.wuzhiaite.netty_demo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {

    /**
     * 1.数据写入byte'buffer
     * 2.bytebuffer 再通过channel写入到文件
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {

        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        
        ByteBuffer bytebuffer = ByteBuffer.allocate((int) file.length());
        //把bytebuffer写入到channel
        fileChannel.read(bytebuffer);
        System.out.println(new String(bytebuffer.array()));
        fileInputStream.close();
    }

}
