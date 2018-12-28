package com.ych.internet.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 字符操作类
 * Created by wangfeiliang on 2018/12/22.
 */
public class ReaderMain {

  public static void main(String[] args) throws IOException {
    //bufferRead();
    //charArrayReader();
    printWrite();
  }

  /**
   *FileReader操作文件，BufferedReader使用了装饰器模式，增加了对字符流操作的缓存能力。
   * @throws IOException
   */
  public static void bufferRead() throws IOException {
    FileReader fileReader=new FileReader("/Users/wangfeiliang/Desktop/src.txt");
    FileWriter fileWriter=new FileWriter("/Users/wangfeiliang/Desktop/target.txt");

    BufferedReader bufferedReader=new BufferedReader(fileReader);
    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

    String line=null;
    while ((line=bufferedReader.readLine())!=null){
      bufferedWriter.write(line);
      bufferedWriter.newLine();
    }

    bufferedWriter.flush();
    bufferedReader.close();
    bufferedWriter.close();
  }

  public static void charArrayReader() throws IOException {
    String content="你好,Java Blocking I/O!";
    CharArrayReader charArrayReader=new CharArrayReader(content.toCharArray());

    char[] chars=new char[1024];
    int size=0;
    CharArrayWriter charArrayWriter=new CharArrayWriter();
    while ((size=charArrayReader.read(chars))!=-1){
      charArrayWriter.write(chars,0,size);
    }
    System.out.println(charArrayWriter.toString());
    char[] charArray=charArrayWriter.toCharArray();
    for(char c:charArray){
      System.out.println(c);
    }
  }


  public static void printWrite() throws FileNotFoundException {
    PrintWriter printWriter=new PrintWriter("/Users/wangfeiliang/Desktop/target.txt");
    printWriter.printf("你好,%s %s %s %s","java","blocking","I/O","!");
    printWriter.flush();
    printWriter.close();
  }

}
