package com.ych.internet.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务端I/O时间处理类
 */
public class EchoServerHandler extends SimpleChannelInboundHandler {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    //接受客户端发来的数据,使用buf.readableBytes()获取数据大小，并转换成bytes数组
    ByteBuf buf= (ByteBuf) msg;
    byte[] req=new byte[buf.readableBytes()];
    buf.readBytes(req);

    //将byte数组转换成字符串,在控制台打印
    String body=new String(req,"UTF-8");
    System.out.println("receive data from client:"+body);

    //将从客户端接受的数据回传给客户端
    ByteBuf resp= Unpooled.copiedBuffer(body.getBytes());
    ctx.write(resp);
    //将发送缓存区中的消息全部写入SocketChannel中
    ctx.flush();

  }
}
