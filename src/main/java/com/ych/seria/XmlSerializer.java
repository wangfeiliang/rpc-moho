package com.ych.seria;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlSerializer implements ISerializer {

  private static final XStream xStream=new XStream(new DomDriver());

  @Override
  public <T> byte[] serialize(T obj) {
    return xStream.toXML(obj).getBytes();
  }

  @Override
  public <T> T deserialize(byte[] data, Class<T> clazz) {
    String xml=new String(data);
    System.out.println("xStream xml:"+xml);
    return (T) xStream.fromXML(xml);
  }
}
