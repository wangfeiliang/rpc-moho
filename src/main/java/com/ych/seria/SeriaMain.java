package com.ych.seria;

import com.alibaba.fastjson.JSON;

public class SeriaMain {

  public static void main(String[] args) {
    ISerializer iSerializer=new DefaultJavaSerializer();
    User user=new User("wangfeiliang",28,1,"北京市");
    byte[] bytes=iSerializer.serialize(user);
    System.out.println("DefaultJavaSerializer serialize:"+bytes.length);
    User serUser=iSerializer.deserialize(bytes,User.class);
    System.out.println(JSON.toJSONString(serUser));
  }
}
