package com.evandro.cards.core;

public class Util {

  public static String firstLetterUppercase(String str) { return str.substring(0, 1).toUpperCase() + str.substring(1); }

  public static String padWithZeroLeft(int value, int size) {
    StringBuilder result = new StringBuilder(Integer.toString(value));
    while (result.length() < size) { result.insert(0, "0"); }
    return result.toString();
  }

}