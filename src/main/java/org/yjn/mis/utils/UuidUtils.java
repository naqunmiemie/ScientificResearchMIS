package org.yjn.mis.utils;

import java.util.UUID;


public class UuidUtils {

  public static String next(){
    return UUID.randomUUID().toString().replace("-","a");
  }

}
