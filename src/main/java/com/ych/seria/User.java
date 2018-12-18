package com.ych.seria;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by wangfeiliang on 2018/12/16.
 */
@Data
public class User implements Serializable{
   private String name;
   private Integer age;
   private Integer sex;
   private String address;

   public User() {
   }

   public User(String name, Integer age, Integer sex, String address) {
      this.name = name;
      this.age = age;
      this.sex = sex;
      this.address = address;
   }
}
