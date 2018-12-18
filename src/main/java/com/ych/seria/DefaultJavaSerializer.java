package com.ych.seria;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wangfeiliang on 2018/12/16.
 */
public class DefaultJavaSerializer implements ISerializer {

  @Override
  public <T> byte[] serialize(T obj) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(obj);
      objectOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return byteArrayOutputStream.toByteArray();
  }

  @Override
  public <T> T deserialize(byte[] data, Class<T> clazz) {
    ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
    try {
      ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
      return (T) objectInputStream.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
