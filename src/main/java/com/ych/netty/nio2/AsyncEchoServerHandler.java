package com.ych.netty.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncEchoServerHandler implements Runnable{

  private int port;
  CountDownLatch latch;

  AsynchronousServerSocketChannel asynchronousServerSocketChannel;

  public AsyncEchoServerHandler(int port) {
    this.port = port;
    try {
      AsynchronousServerSocketChannel.open();
      //绑定服务端口
      asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    latch=new CountDownLatch(1);
    doAccept();
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void doAccept(){
    asynchronousServerSocketChannel.accept(this,null );
  }
}
