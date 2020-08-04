package com.wuzhiaite.netty_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootTest
class NettyDemoApplicationTests {

    @Test
    void contextLoads() throws IOException {
        ServerSocket server = new ServerSocket();
        Socket socket = server.accept();


    }

}
