package com.evandro.cards.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evandro.cards.dao.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

  private final SQLiteDatabase db;

  public PersonDAO(Context context) {
    DBHelper dbHelper = new DBHelper(context);
    db = dbHelper.getWritableDatabase();
  }

  public List<String> getAll() {
    List<String> people = new ArrayList<>();
    Cursor cursor  = db.query(DBHelper.TABLE_PEOPLE, new String[]{"person"},
        null, null, null, null, null);

    while (cursor.moveToNext()) {
      people.add(cursor.getString(0));
    }

    return people;
  }

  public void inserir(String person) {
    ContentValues values = new ContentValues();
    values.put("person", person);
    db.insert(DBHelper.TABLE_PEOPLE, null, values);
  }

}