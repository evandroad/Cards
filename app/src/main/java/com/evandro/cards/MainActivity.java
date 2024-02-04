package com.evandro.cards;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.evandro.cards.core.Globally;
import com.evandro.cards.core.MainAdapter;
import com.evandro.cards.core.MyDate;
import com.evandro.cards.core.Transaction;
import com.evandro.cards.dao.TransactionDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  final Globally globally = Globally.getInstance();
  final MyDate myDate = MyDate.getInstance();
  Spinner spCards, spPeople;
  Activity activity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    activity = this;

    spCards = findViewById(R.id.spCards);
    spPeople = findViewById(R.id.spPeople);
    fillScreen();
  }

  private void date() {
    TextView tvMonthYear = findViewById(R.id.tvMonthYear);
    tvMonthYear.setText(myDate.getMonthYear());

    Button btnLess = findViewById(R.id.btnLessMonth);
    btnLess.setOnClickListener(v -> {
      myDate.lessMonth();
      fillScreen();
    });

    Button btnMore = findViewById(R.id.btnMoreMonth);
    btnMore.setOnClickListener(v -> {
      myDate.moreMonth();
      fillScreen();
    });
  }

  private void fillScreen() {
    date();
    globally.init(this);

    ArrayAdapter<String> cardAdapter = new ArrayAdapter<>(
      this,
      R.layout.item_spinner,
      R.id.txtSpinner,
      globally.getCards()
    );
    spCards.setAdapter(cardAdapter);

    ArrayAdapter<String> personAdapter = new ArrayAdapter<>(
      this,
      R.layout.item_spinner,
      R.id.txtSpinner,
      globally.getPeople()
    );
    spPeople.setAdapter(personAdapter);

    ListView list = findViewById(R.id.list);
    MainAdapter adapter;
    list.invalidateViews();
    TransactionDAO transactionDAO = new TransactionDAO(this);
    adapter = new MainAdapter(activity, transactionDAO.getAll());
    list.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
//      case R.id.itemUpdate:
//        myDate.setToday();
//        update();
//        break;
      case R.id.action_cards:
//        BankActivity.myTitle = new MyTitle("Itaú", "Michelle");
        intentTo(FormCardActivity.class);
        break;
      case R.id.action_people:
//        BankActivity.myTitle = new MyTitle("Nubank", "Michelle");
        intentTo(FormPersonActivity.class);
        break;
      case R.id.action_insert:
        intentTo(FormTransactionActivity.class);
    }

    return super.onOptionsItemSelected(item);
  }

  public void intentTo(Class<?> activity) {
    Intent it = new Intent(this, activity).setAction("your.custom.action");
    startActivity(it);
  }

  @Override
  public void onResume() {
    fillScreen();
    super.onResume();
  }

}