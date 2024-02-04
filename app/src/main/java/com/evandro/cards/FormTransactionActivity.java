package com.evandro.cards;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.evandro.cards.core.Alert;
import com.evandro.cards.core.Globally;
import com.evandro.cards.core.MyDate;
import com.evandro.cards.core.Transaction;
import com.evandro.cards.core.Util;
import com.evandro.cards.dao.TransactionDAO;

import java.util.List;

public class FormTransactionActivity extends AppCompatActivity {

  Spinner spDescription, spPerson, spCard, spHolder;
  EditText txtValue, txtInstallment;
  Button btn;
  Context context;
  ProgressDialog progressDialog;
  final MyDate myDate = MyDate.getInstance();
  final Globally globally = Globally.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_transaction);

    spDescription = findViewById(R.id.spDescription);
    spPerson = findViewById(R.id.spPerson);
    txtValue = findViewById(R.id.txtValue);
    txtInstallment = findViewById(R.id.txtInstallment);
    spCard = findViewById(R.id.spCard);
    spHolder = findViewById(R.id.spHolder);
    btn = findViewById(R.id.btnInsertTransaction);

    context = this;

//    setTitle(CardActivity.myTitle.toString());

//    txtValue.addTextChangedListener(new MoneyTextWatcher(txtValue));

    fillSpinner(globally.getDescriptions(), spDescription);
    fillSpinner(globally.getPeople(), spPerson);
    fillSpinner(globally.getCards(), spCard);
    fillSpinner(globally.getPeople(), spHolder);

    date();

    btn.setOnClickListener(v -> crud());
  }

  private void date() {
    TextView tvMonthYear = findViewById(R.id.tvMonthYear);
    tvMonthYear.setText(myDate.getMonthYear());

    Button btnLess = findViewById(R.id.btnLessMonth);
    btnLess.setOnClickListener(v -> {
      myDate.lessMonth();
      tvMonthYear.setText(myDate.getMonthYear());
    });

    Button btnMore = findViewById(R.id.btnMoreMonth);
    btnMore.setOnClickListener(v -> {
      myDate.moreMonth();
      tvMonthYear.setText(myDate.getMonthYear());
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_card, menu);
    return true;
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_cards:
        intentTo(FormCardActivity.class);
        break;
      case R.id.action_people:
        intentTo(FormPersonActivity.class);
        break;
      case R.id.action_descriptions:
        intentTo(FormDescriptionActivity.class);
    }

    return super.onOptionsItemSelected(item);
  }

  public void intentTo(Class<?> activity) {
    Intent it = new Intent(this, activity);
    startActivity(it);
  }

  private void crud() {
    if (checkFields()) {
      return;
    }

    TransactionDAO transactionDAO = new TransactionDAO(this);
    progressDialog = Alert.alertLoading(context);
    progressDialog.show();

    Transaction transaction = new Transaction();
    transaction.setDate(myDate.getDate());
    transaction.setPerson(spPerson.getSelectedItem().toString());
    transaction.setDescription(spDescription.getSelectedItem().toString());
    transaction.setValue(Util.moneyToString(txtValue.getText().toString()));
    transaction.setInstallment(txtInstallment.getText().toString());
    transaction.setCard(spCard.getSelectedItem().toString());
    transaction.setHolder(spHolder.getSelectedItem().toString());

    transactionDAO.inserir(transaction);
    progressDialog.dismiss();
    Alert.alertSuccess(this, "Cartão inserido com sucesso").show();

    txtValue.setText("");
    txtInstallment.setText("");
  }

  private boolean checkFields() {
    if (spDescription.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(context, "Preencha a descrição.").show();
      return true;
    }

    if (spPerson.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(context, "Preencha a pessoa.").show();
      return true;
    }

    if (txtValue.getText().toString().isEmpty()) {
      Alert.alertInfo(context, "Preencha o valor.").show();
      return true;
    }

    if (txtInstallment.getText().toString().isEmpty()) {
      Alert.alertInfo(context, "Preencha a parcela.").show();
      return true;
    }

    return false;
  }

  private void fillSpinner(List<String> list, Spinner spinner) {
    if (list == null) {
      return;
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.txtSpinner, list);
    spinner.setAdapter(adapter);
  }

}