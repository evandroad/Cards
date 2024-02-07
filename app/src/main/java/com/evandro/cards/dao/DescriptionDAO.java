package com.evandro.cards.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DescriptionDAO {

  private final SQLiteDatabase db;

  public DescriptionDAO(Context context) {
    DBHelper dbHelper = new DBHelper(context);
    db = dbHelper.getWritableDatabase();
  }

  public List<String> getAll() {
    List<String> description = new ArrayList<>();
    Cursor cursor  = db.query(DBHelper.TABLE_DESCRIPTIONS, new String[]{"description"},
        null, null, null, null, null);

    while (cursor.moveToNext()) {
      description.add(cursor.getString(0));
    }

    return description;
  }

  public void insert(String description) {
    ContentValues values = new ContentValues();
    values.put("description", description);
    db.insert(DBHelper.TABLE_DESCRIPTIONS, null, values);
  }

  public void update(String currentDescription, String newDescription) {
    ContentValues values = new ContentValues();
    values.put("description", newDescription);
    db.update(DBHelper.TABLE_DESCRIPTIONS, values, "description=?", new String[]{currentDescription});
  }

  public void delete(String description) {
    db.delete(DBHelper.TABLE_DESCRIPTIONS, "description=?", new String[]{description});
  }

}