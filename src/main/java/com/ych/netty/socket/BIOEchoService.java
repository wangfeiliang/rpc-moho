package com.ych.netty.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangfeiliang on 2018/12/22.
 */
public class BIOEchoService {

  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  public static void main(String[] args) {
    int port=8082;
    ServerSocket serverSocket=null;
    try {
      serverSocket=new ServerSocket(port);
      Socket socket=null;
      while (true){
        System.out.println("socket accept before");
        socket=serverSocket.accept();
        System.out.println("socket:"+socket);
        executorService.submit(new BIOEchoServerHandler(socket));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if(serverSocket!=null){
        try {
          serverSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
