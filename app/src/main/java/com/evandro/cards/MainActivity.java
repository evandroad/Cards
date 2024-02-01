package com.evandro.cards;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.evandro.cards.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  private AppBarConfiguration appBarConfiguration;
  private ActivityMainBinding binding;
  private List<String> cards;
  private CardDAO cardDAO;
  final Globally globally = Globally.getInstance();
  Spinner spCards, spPeople;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setSupportActionBar(binding.toolbar);

    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    cardDAO = new CardDAO(this);
    cards = cardDAO.getAll();

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
      NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
      navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
      return true;
    }

    if (id == R.id.action_people) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
  }

}