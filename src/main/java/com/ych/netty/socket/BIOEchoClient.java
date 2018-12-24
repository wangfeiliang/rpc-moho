package com.ych.netty.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by wangfeiliang on 2018/12/22.
 */
public class BIOEchoClient {

  public static void main(String[] args) {
    int port=8082;
    String serviceIp="127.0.0.1";
    Socket socket=null;
    BufferedReader reader=null;
    BufferedWriter writer=null;

    try {
      socket=new Socket(serviceIp,port);
      reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      writer.write("hello,block io"+"\n");
      writer.flush();

      System.out.println("client send params");
      String echo=reader.readLine();
      System.out.println("echo:"+echo);

    } catch (IOException e) {
      e.printStackTrace();
    }finally {

      if(reader!=null){
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(socket!=null){
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

}
