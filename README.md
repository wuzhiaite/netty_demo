### netty_demo

目的：用于学习netty相关基础知识

内容：1.netty服务器和客户端代码测试


## 通道channel
通道的读写操作

1.读操作

2.写操作

3.用一个FileChannel和read,write完成文件拷贝。

4.图片传输(transferFrom)

- 注意事项：
 
  - BufferUnderflowException异常，放入和取出的类型应该一致。
  
  - 可以将普通的ByteBuffer转成只读buffer

  - MappedByteBuffer 直接在内存中进行修改















