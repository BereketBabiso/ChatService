package com.chat.simple.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatServiceUtility {


  public static boolean isStringNotEmpty(String str) {
    return str != null && str.trim().length() != 0;
  }

  public static boolean isAllStringsNotEmpty(String... strs) {
    for (String s : strs) {
      if (!isStringNotEmpty(s))
        return false;
    }
    return true;
  }

  public static boolean isAllStringsEmpty(String... strs) {
    for (String s : strs) {
      if (isStringNotEmpty(s))
        return false;
    }
    return true;
  }
  
  public static Date formatCurrentTimeStamp(SimpleDateFormat sdf) {
    try {
      Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
      String date = sdf.format(currentTimestamp);
      Date currentDate = sdf.parse(date);
      return currentDate;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
