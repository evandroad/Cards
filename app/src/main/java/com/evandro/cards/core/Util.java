package com.evandro.cards.core;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.evandro.cards.R;

import java.util.List;

public class Util {

  public static String firstLetterUppercase(String str) { return str.substring(0, 1).toUpperCase() + str.substring(1); }

  public static String padWithZeroLeft(int value, int size) {
    StringBuilder result = new StringBuilder(Integer.toString(value));
    while (result.length() < size) { result.insert(0, "0"); }
    return result.toString();
  }

  public static String moneyToString(String value) {
    value = value.replace(".", "");
    value = value.replace(",", ".");
    return value;
  }

  public static String removeLastChar(String string) {
    if (!string.isEmpty()) {
      string = string.replaceFirst(".$", "");
    }

    return string;
  }

  public static String removeStart(String str, int qtt) { return str.substring(qtt); }

  public static void fillSpinner(Context context, List<String> list, Spinner spinner) {
    if (list == null || context == null || spinner == null) {
      return;
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.txtSpinner, list);
    spinner.setAdapter(adapter);
  }

}