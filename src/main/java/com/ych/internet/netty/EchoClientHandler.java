package com.ych.internet.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理客户端I/事件类
 */
public class EchoClientHandler extends SimpleChannelInboundHandler {

  /**
   * 服务端响应请求数据返回数据的时候回自动调用该方法，我们通过实现该方法来接受服务端返回的数据
   * 进行客户端逻辑
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf buf= (ByteBuf) msg;
    byte[] req=new byte[buf.readableBytes()];
    buf.readBytes(req);

    String body=new String(req,"UTF-8");
    System.out.println("receive data from server:"+body);
  }
}
