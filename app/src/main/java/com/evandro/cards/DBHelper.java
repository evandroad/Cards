package com.evandro.cards;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "data_cards.db";
  private static final int DATABASE_VERSION = 1;

  public DBHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String createTableTransaction = "CREATE TABLE IF NOT EXISTS transactions (" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "date DATE, " +
        "description TEXT, " +
        "value TEXT, " +
        "holder TEXT, " +
        "bank TEXT, " +
        "installment TEXT, " +
        "person TEXT)";
    db.execSQL(createTableTransaction);

    String createTableCards = "CREATE TABLE IF NOT EXISTS cards (card TEXT)";
    db.execSQL(createTableCards);

    String createTablePeople = "CREATE TABLE IF NOT EXISTS people (person TEXT)";
    db.execSQL(createTablePeople);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}