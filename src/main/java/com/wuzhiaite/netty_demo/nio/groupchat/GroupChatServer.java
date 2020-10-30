package com.wuzhiaite.netty_demo.nio.groupchat;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    //初始化工作
    public GroupChatServer(){

        try {
            //得到选择器
            selector = Selector.open();
            //通道
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置阻塞模式
            listenChannel.configureBlocking(false);
            //将该listenChannel，注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //监听
    public void listen (){

        try {
            while(true){
                int count = selector.select(2000);
                if( count > 0 ){//有事件处理
                    //遍历selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();

                        //监听accept
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            //将sc注册到selector
                            sc.register(selector,SelectionKey.OP_READ);

                            System.out.println(sc.getRemoteAddress() + "上线");
                            if(key.isReadable()){//通道发生read事件，即通道是可读的状态
                                //处理读的方法
                                readData(key);
                            }
                            //当前的key删除，防止重复处理
                            iterator.remove();


                        }
                    }
                }else{
                    System.out.println("等待。。。。");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //读取客户端消息
    private void readData(SelectionKey key){

        SocketChannel channel = null ;

        try {
            //取得关联的channel
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            //根据count的值做处理
            if(count > 0){
                //把缓存区的数据转成字符串
                String msg = new String(buffer.array());
                System.out.println("from client :"+msg);
                //向其他的客户端转发消息，专门写一个方法来处理
                sendInfoToOtherClients(msg,channel);


            }


        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }


    //转发消息给其他客户（通道）
    private void sendInfoToOtherClients(String msg,SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中。。。。。。。");
        //遍历所有注册到selector上的SocketChannel,并排除自己
        for(SelectionKey key : selector.keys()){
            //通过key取出对应的SocketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if(targetChannel instanceof SocketChannel && targetChannel != self){
                //转型
                SocketChannel dest = (SocketChannel)targetChannel;
                //将msg存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer的数据写入通道
                dest.write(buffer);

            }

        }
    }





}
