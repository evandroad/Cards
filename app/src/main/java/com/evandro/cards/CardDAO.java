package com.evandro.cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CardDAO {

  private final SQLiteDatabase db;

  public CardDAO(Context context) {
    DBHelper dbHelper = new DBHelper(context);
    db = dbHelper.getWritableDatabase();
  }

  public List<String> getAll() {
    List<String> cards = new ArrayList<>();
    Cursor cursor  = db.query(DBHelper.TABLE_CARDS, new String[]{"card"},
        null, null, null, null, null);

    while (cursor.moveToNext()) {
      cards.add(cursor.getString(0));
    }

    return cards;
  }

  public void inserir(String card) {
    ContentValues values = new ContentValues();
    values.put("card", card);
    db.insert("cards", null, values);
  }

}