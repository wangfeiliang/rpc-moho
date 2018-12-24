package com.ych.netty.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * Created by wangfeiliang on 2018/12/24.
 */
public class FilesDemo {

  public static void main(String[] args) throws IOException {
    //获取liunx根目录下/的左右子目录
    DirectoryStream<Path> directoryStream= Files.newDirectoryStream(Paths.get("/"));
    Iterator<Path> iterator=directoryStream.iterator();
    while (iterator.hasNext()){
      Path path=iterator.next();
      System.out.println(path);
    }
    //递归创建目录
    Path path=Files.createDirectories(Paths.get("/Users/wangfeiliang/test/test"));
    System.out.println(path.getFileName());

    //创建文件
    Files.deleteIfExists(Paths.get("/Users/wangfeiliang/test/test/test.txt"));
    Path file=Files.createFile(Paths.get("/Users/wangfeiliang/test/test/test.txt"));
    System.out.println("file:"+file.toAbsolutePath());

    //使用缓存字符流写入文件内容
    Charset charset=Charset.forName("UTF-8");
    String text="Hello!,Java NIO2";
    BufferedWriter bufferedWriter=Files.newBufferedWriter(file,charset, StandardOpenOption.APPEND);
    bufferedWriter.write(text);
    bufferedWriter.close();

    //使用缓存字符流读取文件内容
    BufferedReader bufferedReader=Files.newBufferedReader(file,charset);
    String line=null;
    while ((line=bufferedReader.readLine())!=null){
      System.out.println(line);
    }
    bufferedReader.close();
  }
}
