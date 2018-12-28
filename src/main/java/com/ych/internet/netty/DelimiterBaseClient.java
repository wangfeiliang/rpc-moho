package com.ych.internet.netty;

import io.netty.bootstrap.Bootstrap;
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

public class DelimiterBaseClient {

  //字符串分隔符
  private static final String delimiter_tag="@#";

  public static void main(String[] args) {
    int port=8080;
    new DelimiterBaseClient().connect(port,"127.0.0.1");
  }

  public void connect(int port ,String hosts){
    EventLoopGroup group=new NioEventLoopGroup();

    try{
      Bootstrap b=new Bootstrap();
      b.group(group)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.TCP_NODELAY,true)
          .handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
             ByteBuf delimiter=Unpooled.copiedBuffer(delimiter_tag.getBytes());
             channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
             channel.pipeline().addLast(new StringDecoder());
             channel.pipeline().addLast(new DelimiterBaseClientHandler());
            }
          });
      //发起异步请求
      ChannelFuture f=b.connect(hosts,port).sync();
      //循环调用1000次
      for(int i=0;i<1000;i++){
        byte[] req=("你好,Netty"+i).getBytes();
        ByteBuf messageBuffer= Unpooled.buffer(req.length);
        messageBuffer.writeBytes(req);

        //向服务端发送数据
        ChannelFuture channelFuture=f.channel().writeAndFlush(messageBuffer);
        channelFuture.syncUninterruptibly();
      }
      //等待客户端链路关闭
      f.channel().closeFuture().sync();
    }catch(Exception e){
      e.printStackTrace();
    }finally {
      group.shutdownGracefully();
    }

  }
}
