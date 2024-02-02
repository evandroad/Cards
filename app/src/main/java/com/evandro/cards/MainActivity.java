package com.evandro.cards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.evandro.cards.core.Globally;
import com.evandro.cards.core.MainAdapter;
import com.evandro.cards.core.Transaction;
import com.evandro.cards.dao.CardDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  final Globally globally = Globally.getInstance();
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

  private void fillScreen() {
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

    List<Transaction> transactions = null;
    ListView list = findViewById(R.id.list);
    MainAdapter adapter;
    list.invalidateViews();
    adapter = new MainAdapter(activity, transactions);
    list.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_cards) {
      intentTo(FormCardActivity.class);
      return true;
    }

    if (id == R.id.action_people) {
      intentTo(FormPersonActivity.class);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void intentTo(Class<?> activity) {
    Intent it = new Intent(this, activity).setAction("your.custom.action");
    startActivity(it);
  }

}