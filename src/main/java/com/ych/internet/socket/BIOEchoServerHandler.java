package com.ych.internet.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by wangfeiliang on 2018/12/22.
 */
public class BIOEchoServerHandler implements Runnable{

  private Socket socket;
  public BIOEchoServerHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    BufferedWriter writer=null;
    BufferedReader reader=null;

    try {
      reader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      writer=new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
      while (true){
        String line=reader.readLine();
        System.out.println("server line:"+line);
        if(line==null){
          break;
        }
        writer.write(line+"收到了"+"\n");
        writer.flush();
      }
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
