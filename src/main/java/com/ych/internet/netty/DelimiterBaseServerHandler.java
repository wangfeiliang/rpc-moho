package com.ych.internet.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.concurrent.atomic.AtomicInteger;

public class DelimiterBaseServerHandler extends SimpleChannelInboundHandler {

  //字符串分隔符
  private static final String delimiter_tag="@#";
  /**
   * 计数器
   */
  private static final AtomicInteger counter=new AtomicInteger(0);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    //接受客户端发送的字符串,并打印到控制台
    String content= (String) msg;
    System.out.println("received from client:"+content+"counter:"+counter.addAndGet(1));
    //加入分隔符,将数据从新发送到客户端
    content+=delimiter_tag;
    ByteBuf echo= Unpooled.copiedBuffer(content.getBytes());
    ctx.writeAndFlush(echo);
    ctx.flush();
  }
}
