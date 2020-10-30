package com.wuzhiaite.netty_demo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {

    /**
     * 读取文件到bytebuffer,再写入到channel
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("d:\\1.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\2.jpg");

        FileChannel s = fileInputStream.getChannel();
        FileChannel d = fileOutputStream.getChannel();

        d.transferFrom(s,0,s.size());

        //关闭流
        s.close();
        d.close();
        fileInputStream.close();
        fileOutputStream.close();





    }

}
