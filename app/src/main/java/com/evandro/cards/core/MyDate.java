package com.evandro.cards.core;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyDate {

  private final DateTimeFormatter sqlFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
  private DateTime dateTime;
  private static MyDate instance = null;

  private MyDate() {}

  public static MyDate getInstance() {
    if (instance == null) {
      instance = new MyDate();
      instance.setToday();
    }

    return instance;
  }

  public List<String> getListMonths() {
    return Arrays.asList(
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    );
  }

  public List<String> getListDays() { return Arrays.asList("", "01", "15"); }

  public void setToday() { dateTime = new DateTime(); }

  public String getSqlDate() { return dateTime.toString(sqlFormat); }

  public Date getDate() { return dateTime.toDate(); }

  public String getMonthYear() {
    DateTimeFormatter myf = DateTimeFormat.forPattern("MMMM/Y")
        .withLocale(new Locale("pt", "BR"));

    return Util.firstLetterUppercase(dateTime.toString(myf));
  }

  public String getWeekDay() {
    DateTimeFormatter wdf = DateTimeFormat.forPattern("E/d")
        .withLocale(new Locale("pt", "BR"));

    return Util.firstLetterUppercase(dateTime.toString(wdf));
  }

  public String getFirstDayMonth() { return dateTime.dayOfMonth().withMinimumValue().toString(sqlFormat); }

  public String getMiddleDayMonth() { return dateTime.withDayOfMonth(15).toString(sqlFormat); }

  public String getLastDayMonth() { return dateTime.dayOfMonth().withMaximumValue().toString(sqlFormat); }

  public String getDateFromDay(String day) {
    if (day.equals("01")) {
      return getFirstDayMonth();
    }

    return getMiddleDayMonth();
  }

  public String getDateFromMonth(int month, String day) {
    month++;
    return getYear() + "-" + Util.padWithZeroLeft(month, 2) + "-" + day;
  }

  public String getDayFromDate(String date) {
    DateTime dateTime = sqlFormat.parseDateTime(date);
    DateTimeFormatter df = DateTimeFormat.forPattern("dd");
    return dateTime.toString(df);
  }

  public String getMonth() { return dateTime.toString(DateTimeFormat.forPattern("MM")); }

  public String getYear() { return dateTime.toString(DateTimeFormat.forPattern("yyyy")); }

  public String getWeekDay(String date) {
    DateTime dateTime = sqlFormat.parseDateTime(date);
    DateTimeFormatter df = DateTimeFormat.forPattern("E")
        .withLocale(new Locale("pt", "BR"));
    return Util.firstLetterUppercase(dateTime.toString(df));
  }

  public void moreDay() { dateTime = dateTime.plusDays(1); }

  public void lessDay() { dateTime = dateTime.minusDays(1); }

  public void moreMonth() { dateTime = dateTime.plusMonths(1); }

  public void lessMonth() { dateTime = dateTime.minusMonths(1); }

}