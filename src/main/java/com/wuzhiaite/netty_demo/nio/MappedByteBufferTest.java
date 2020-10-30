package com.wuzhiaite.netty_demo.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {


    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        //参数1：表示使用读写模式；
        // 参数2：表示可以直接修改的起始位置；
        // 参数3：表示映射到内存大小，即将1.txt的多少个字节映射到内存。
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');

    }









}
