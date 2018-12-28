package com.ych.internet.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by wangfeiliang on 2018/12/23.
 */
public class BufferMain {

  public static void main(String[] args) {
     apiTest();
  }

  public static void init(){
    Buffer buffer= ByteBuffer.allocate(10);
    System.out.println("capacity:"+buffer.capacity());
    System.out.println("limit:"+buffer.limit());
    System.out.println("position:"+buffer.position());
    System.out.println("remianing:"+buffer.remaining());

    System.out.println("设置buffer的limit属性为6");
    buffer.limit(6);
    System.out.println("limit:"+buffer.limit());
    System.out.println("position:"+buffer.position());
    System.out.println("remianing:"+buffer.remaining());
    buffer.position(2);
    System.out.println("position:"+buffer.position());
    System.out.println("remaining:"+buffer.remaining());

    System.out.println(buffer);
  }

  public static void apiTest(){
    String content="你好！java Non-Blocking I/O.";
    CharBuffer buffer=CharBuffer.allocate(50);
    for(char c:content.toCharArray()){
      buffer.put(c);
    }
    //反转buffer,准备读取buffer内容
    buffer.flip();
    while (buffer.hasRemaining()){
      System.out.print(buffer.get());
    }

  }
}
