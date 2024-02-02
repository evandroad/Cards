package com.evandro.cards.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "data_cards.db";
  public static final String TABLE_CARDS = "cards";
  public static final String TABLE_PEOPLE = "people";
  public static final String TABLE_TRANSACTIONS = "transactions";
  private static final int DATABASE_VERSION = 1;

  public DBHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String createTableTransaction = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTIONS + " (" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "date DATE, " +
        "description TEXT, " +
        "value TEXT, " +
        "holder TEXT, " +
        "bank TEXT, " +
        "installment TEXT, " +
        "person TEXT)";
    db.execSQL(createTableTransaction);

    String createTableCards = "CREATE TABLE IF NOT EXISTS " + TABLE_CARDS + " (card TEXT)";
    db.execSQL(createTableCards);

    String createTablePeople = "CREATE TABLE IF NOT EXISTS " + TABLE_PEOPLE + " (person TEXT)";
    db.execSQL(createTablePeople);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}