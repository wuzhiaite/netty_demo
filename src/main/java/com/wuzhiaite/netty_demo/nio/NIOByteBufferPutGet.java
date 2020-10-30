package com.wuzhiaite.netty_demo.nio;

import java.nio.ByteBuffer;

public class NIOByteBufferPutGet {


    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(100);
        buffer.putLong(1);
        buffer.putChar('哟');
        buffer.putShort((short)5);
        buffer.flip();//取
        System.out.println();
        System.out.println(buffer.getLong());
        System.out.println(buffer.getInt());

        System.out.println(buffer.getChar());

        System.out.println(buffer.getShort());
        String str = "test";
        int i = str.hashCode();

    }
}
