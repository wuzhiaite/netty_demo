package com.wuzhiaite.netty_demo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {

    /**
     * 读取文件到bytebuffer,再写入到channel
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel channel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while(true){
            byteBuffer.clear();//清空一下属性。
            int read = channel01.read(byteBuffer);
            System.out.println("read="+read);
            if(read == -1){
                break;
            }
            //将buffer中的数据写入到channel
            byteBuffer.flip();//进行反转，读写反转
            channel02.write(byteBuffer);

        }

        //关闭流
        fileInputStream.close();
        fileOutputStream.close();





    }

}
