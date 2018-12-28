package com.ych.internet.stream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.ych.seria.User;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * 字节操作类
 * Created by wangfeiliang on 2018/12/20.
 */
public class StreamMain {

  public static void main(String[] args) throws IOException {
       //byteArray();
       //file();
    //buffered();
    //data();
    //print();
    object();
  }

  public static void byteArray() throws IOException {
    String content="你好,java Blocking I/O!";
    byte[] inputBytes=content.getBytes(Charset.forName("UTF-8"));
    ByteArrayInputStream inputStream=new ByteArrayInputStream(inputBytes);

    byte[] bytes=new byte[1024];
    int size=0;
    ByteOutputStream outputStream=new ByteOutputStream();
    while ((size=inputStream.read(bytes))!=-1){
      outputStream.write(bytes,0,size);
    }
    System.out.println(outputStream.toString());
  }


  public static void file(){
    Long start=System.currentTimeMillis();
    FileInputStream inputStream=null;
    FileOutputStream outputStream=null;
    File srcFile=new File("/Users/wangfeiliang/Desktop/src.txt");
    File targetFile=new File("/Users/wangfeiliang/Desktop/target.txt");
    try {
      inputStream=new FileInputStream(srcFile);
      outputStream=new FileOutputStream(targetFile);
      int byt;
      while ((byt=inputStream.read())!=-1){
        outputStream.write(byt);
      }
      System.out.println(System.currentTimeMillis()-start);
    }catch (Exception e){
      e.printStackTrace();
    } finally {
      if(inputStream!=null){
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(outputStream!=null){
        try {
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * 将字节流写入该缓存类,再一次性输出，提高io处理性能。
   */
  public static void buffered(){
    Long start=System.currentTimeMillis();
    FileInputStream inputStream=null;
    FileOutputStream outputStream=null;
    BufferedInputStream bufferedInputStream=null;
    BufferedOutputStream bufferedOutputStream=null;
    File srcFile=new File("/Users/wangfeiliang/Desktop/src2.txt");
    File targetFile=new File("/Users/wangfeiliang/Desktop/target2.txt");
    try {
      inputStream=new FileInputStream(srcFile);
      bufferedInputStream=new BufferedInputStream(inputStream);
      outputStream=new FileOutputStream(targetFile);
      bufferedOutputStream=new BufferedOutputStream(outputStream);
      byte[] buff=new byte[1024];
      int byt;
      while ((byt=bufferedInputStream.read(buff,0,buff.length))!=-1){
        bufferedOutputStream.write(buff,0,byt);
      }
      bufferedOutputStream.flush();
      System.out.println(System.currentTimeMillis()-start);
    }catch (Exception e){
      e.printStackTrace();
    } finally {
      if(inputStream!=null){
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(outputStream!=null){
        try {
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public static void data(){
    File fileName=new File("/Users/wangfeiliang/Desktop/data.txt");
    try {
      FileOutputStream fileOutputStream=new FileOutputStream(fileName);
      DataOutputStream dataOutputStream=new DataOutputStream(fileOutputStream);
      dataOutputStream.writeInt(2017);
      dataOutputStream.writeUTF("你好,java Blocking I/O!");
      dataOutputStream.writeBoolean(true);

      FileInputStream fileInputStream=new FileInputStream(fileName);
      DataInputStream dataInputStream=new DataInputStream(fileInputStream);

      System.out.println(dataInputStream.readInt());
      System.out.println(dataInputStream.readUTF());
      System.out.println(dataInputStream.readBoolean());

      fileInputStream.close();
      dataInputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void print(){
    File fileName=new File("/Users/wangfeiliang/Desktop/p.txt");
    try {
      PrintStream printStream=new PrintStream(fileName);
      printStream.println("你好,java Blocking I/O!");
      printStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * 序列化
   */
  public static void object(){
    User user=new User("wangfeiliang",28,1,"北京市");
    File userTxt=new File("/Users/wangfeiliang/Desktop/p.txt");
    try {
      FileOutputStream fileOutputStream=new FileOutputStream(userTxt);
      ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(user);
      fileOutputStream.close();
      objectOutputStream.close();

      FileInputStream fileInputStream=new FileInputStream(userTxt);
      ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
      User newUser= (User) objectInputStream.readObject();
      System.out.println(newUser.getName()+":"+newUser.getAddress());
      fileInputStream.close();
      objectInputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
