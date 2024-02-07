package com.evandro.cards.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.evandro.cards.core.Transaction;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDAO {

  private final SQLiteDatabase db;

  public TransactionDAO(Context context) {
    DBHelper dbHelper = new DBHelper(context);
    db = dbHelper.getWritableDatabase();
  }

  @SuppressLint("Range")
  public List<Transaction> getByMonth(Date startDate) {
    List<Transaction> transactions = new ArrayList<>();
    long startTimestamp = startDate.getTime();

    LocalDate localDate = new LocalDate(startDate);
    LocalDate endDate = localDate.dayOfMonth().withMaximumValue();
    long endTimestamp = endDate.toDateTimeAtStartOfDay().getMillis();

    Cursor cursor = db.query(
      DBHelper.TABLE_TRANSACTIONS,
      new String[]{"id", "date", "description", "value", "holder", "card", "installment", "person"},
      "date BETWEEN ? AND ?",
      new String[]{String.valueOf(startTimestamp), String.valueOf(endTimestamp)},
      null,
      null,
      null
    );

    while (cursor.moveToNext()) {
      Transaction t = new Transaction();
      t.setId(cursor.getInt(0));
      t.setPerson(cursor.getString(cursor.getColumnIndex("person")));
      t.setValue(cursor.getString(cursor.getColumnIndex("value")));
      t.setDescription(cursor.getString(cursor.getColumnIndex("description")));
      t.setInstallment(cursor.getString(cursor.getColumnIndex("installment")));
      t.setDate(new Date(cursor.getLong(cursor.getColumnIndex("date"))));

      transactions.add(t);
    }

    return transactions;
  }

  public void inserir(Transaction t) {
    ContentValues values = new ContentValues();
    values.put("date", t.getDate().getTime());
    values.put("person", t.getPerson());
    values.put("description", t.getDescription());
    values.put("value", t.getValue());
    values.put("installment", t.getInstallment());
    values.put("card", t.getCard());
    values.put("holder", t.getHolder());
    db.insert(DBHelper.TABLE_TRANSACTIONS, null, values);
  }

}
