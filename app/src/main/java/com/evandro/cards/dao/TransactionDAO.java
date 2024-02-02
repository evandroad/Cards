package com.evandro.cards.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evandro.cards.core.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDAO {

  private final SQLiteDatabase db;

  public TransactionDAO(Context context) {
    DBHelper dbHelper = new DBHelper(context);
    db = dbHelper.getWritableDatabase();
  }

  public List<Transaction> getAll() {
    List<Transaction> transactions = new ArrayList<>();
    Cursor cursor = db.query(DBHelper.TABLE_CARDS, new String[]{"id", "date", "description", "value", "holder", "bank",
        "installment", "person"},
        null, null, null, null, null);

    while (cursor.moveToNext()) {
      Transaction t = new Transaction();
      t.setId(cursor.getInt(0));
      @SuppressLint("Range") long dateMillis = cursor.getLong(cursor.getColumnIndex("date"));
      Date date = new Date(dateMillis);
      t.setDate(date);

      transactions.add(t);
    }

    return transactions;
  }

  public void inserir(String card) {
    ContentValues values = new ContentValues();
    values.put("card", card);
    db.insert(DBHelper.TABLE_TRANSACTIONS, null, values);
  }

}
