package com.evandro.cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CardDAO {

  private DBHelper dbHelper;
  private SQLiteDatabase db;

  public CardDAO(Context context) {
    dbHelper = new DBHelper(context);
    db = dbHelper.getWritableDatabase();
  }

  public long inserir(String card) {
    ContentValues values = new ContentValues();
    values.put("card", card);
    return db.insert("cards", null, values);
  }

}