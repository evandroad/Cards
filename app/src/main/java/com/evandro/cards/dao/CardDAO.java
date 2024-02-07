package com.evandro.cards.dao;

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
    Cursor cursor = db.query(DBHelper.TABLE_CARDS, new String[]{"card", "holder"},
        null, null, null, null, null);

    while (cursor.moveToNext()) {
      cards.add(cursor.getString(0) + " " + cursor.getString(1));
    }

    return cards;
  }

  public void insert(String card, String holder) {
    ContentValues values = new ContentValues();
    values.put("card", card);
    values.put("holder", holder);
    db.insert(DBHelper.TABLE_CARDS, null, values);
  }

  public void update(String currentCard, String newCard) {
    ContentValues values = new ContentValues();
    values.put("card", newCard);
    db.update(DBHelper.TABLE_CARDS, values, "card=?", new String[]{currentCard});
  }

  public void delete(String card) { db.delete(DBHelper.TABLE_CARDS, "card=?", new String[]{card}); }

}