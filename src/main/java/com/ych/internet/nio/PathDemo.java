package com.ych.internet.nio;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by wangfeiliang on 2018/12/24.
 * JDK1.7引入了Path,Files来操作文件，称为NIO2.
 */
public class PathDemo {

  public static void main(String[] args) {
    //通过工具类Paths获取绝对路径Path
    Path path= Paths.get("/Users/wangfeiliang/Desktop/src.txt");
    System.out.println("path1:"+path);

    path=Paths.get("/Users","wangfeiliang","Desktop","src.txt");
    System.out.println("path2:"+path);

    //相对路径Path
    path=Paths.get("/java/nio2/2017/test.txt");
    System.out.println("path3:"+path);

    path=Paths.get("file:///Users/wangfeiliang/Desktop/src.txt");
    System.out.println("path4:"+path);

    path= FileSystems.getDefault().getPath("/Users/wangfeiliang/Desktop/src.txt");
    System.out.println("path5:"+path);

    File path_to_file=path.toFile();
    System.out.println("path to file name:"+path_to_file.getName());

    URI  path_to_uri=path.toUri();
    System.out.println("path to uri:"+path_to_uri);

    Path path_to_absolute_path=path.toAbsolutePath();
    System.out.println("Path to absolute path:"+path_to_absolute_path);
  }
}
