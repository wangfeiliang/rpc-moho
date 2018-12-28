package com.ych.internet.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoServer {

  public static void main(String[] args) {
    int port=8080;
    new EchoServer().bing(port);
  }

  public void bing(int port){
    //创建两个EventLoopGroup实例，EventLoopGroup是包含一组专门用于处理网络事件的NIO线程组
    EventLoopGroup bossGroup=new NioEventLoopGroup();
    EventLoopGroup workerGroup=new NioEventLoopGroup();
    try{
      //创建服务端辅助启动类ServerBootstrap对象
      ServerBootstrap b=new ServerBootstrap();
      //设置nio线程组
      b.group(bossGroup,workerGroup)
          //设置NioServerSocketChannel，对应于jdk NIO类的ServerSockerChannel
          .channel(NioServerSocketChannel.class)
          //设置TCP参数,连接请求的最大队列长度
          .option(ChannelOption.SO_BACKLOG,1024)
          //设置i/o事件处理类，用来处理消息的编解码以及我们的业务逻辑
          .childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
              nioSocketChannel.pipeline().addLast(new EchoServerHandler());
            }
          });
      //绑定端口，同步等待成功
      ChannelFuture f=b.bind(port).sync();
      //等待服务器监听端口关闭
      f.channel().closeFuture().sync();
    }catch (Exception e){
      e.printStackTrace();
    } finally {
      //优雅退出,释放线程池资源
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
