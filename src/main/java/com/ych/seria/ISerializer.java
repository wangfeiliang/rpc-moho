package com.ych.seria;

/**
 * Created by wangfeiliang on 2018/12/16.
 */
public interface ISerializer {

  /**
   * 序列化
   * @param obj
   * @param <T>
   * @return
   */
  <T> byte[] serialize(T obj);

  /**
   * 反序列化
   * @param data
   * @param clazz
   * @param <T>
   * @return
   */
  <T> T deserialize(byte[] data,Class<T> clazz);
}
