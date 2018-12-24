package com.ych.netty.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Created by wangfeiliang on 2018/12/24.
 */
public class WatchServiceDemo {

   public void watchDir(Path path) throws IOException, InterruptedException {
      WatchService watchService = FileSystems.getDefault().newWatchService();
      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                                  StandardWatchEventKinds.ENTRY_MODIFY,
                                  StandardWatchEventKinds.ENTRY_DELETE);
      while (true){
         final WatchKey key=watchService.take();
         for(WatchEvent<?> watchEvent:key.pollEvents()){
            final Kind<?> kind=watchEvent.kind();
            if(kind==StandardWatchEventKinds.OVERFLOW){
               continue;
            }
            //打印事件类型以及发生事件的文件名称
            final WatchEvent<Path> watchEventPath= (WatchEvent<Path>) watchEvent;
            final Path fileName=watchEventPath.context();
            System.out.println(kind+": "+fileName);
         }
         //重置key
         boolean valid=key.reset();
         //如果key无效(比较监听的文件被删除)，则退出
         if(!valid){
            System.out.println("key 无效，可能改文件已经被删除");
            break;
         }
      }
   }

   public static void main(String[] args) {
      final Path path= Paths.get("/Users/wangfeiliang/test/test/");
      WatchServiceDemo watchServiceDemo=new WatchServiceDemo();
      try {
         watchServiceDemo.watchDir(path);
      } catch (IOException e) {
         e.printStackTrace();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}
