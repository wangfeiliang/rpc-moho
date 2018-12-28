package com.ych.internet.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class DelimiterBaseServer {
  //字符串分隔符
  private static final String delimiter_tag="@#";

  public static void main(String[] args) {
    int port=8080;
    new DelimiterBaseServer().bind(port);
  }

  public void bind(int port){
    EventLoopGroup bossGroup=new NioEventLoopGroup();
    EventLoopGroup workerGroup=new NioEventLoopGroup();

    try{
      ServerBootstrap  b=new ServerBootstrap();
      b.group(bossGroup,workerGroup)
          .option(ChannelOption.SO_BACKLOG,1024)
          .childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
              //设置DelimiterBasedFrameDecoder处理器
              ByteBuf delimiter= Unpooled.copiedBuffer(delimiter_tag.getBytes());
              channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
              //设置StringDecoder处理器
              channel.pipeline().addLast(new StringDecoder());
              channel.pipeline().addLast(new DelimiterBaseServerHandler());
            }
          });
      ChannelFuture f=b.bind(port).sync();
      f.channel().closeFuture().sync();
    }catch (Exception e){

    }finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
