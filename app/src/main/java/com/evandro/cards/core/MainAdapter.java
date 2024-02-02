package com.evandro.cards.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.evandro.cards.R;

import java.util.List;

public class MainAdapter extends BaseAdapter {

  private final List<Transaction> transactions;
  private final Activity activity;

  public MainAdapter(Activity activity, List<Transaction> transactions) {
    this.activity = activity;
    this.transactions = transactions;
  }

  @Override
  public int getCount() { return transactions.size(); }

  @Override
  public Object getItem(int position) { return transactions.get(position); }

  @Override
  public long getItemId(int position) { return 0; }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    @SuppressLint("ViewHolder")
    View v = activity.getLayoutInflater().inflate(R.layout.item_main, viewGroup, false);
    TextView person = v.findViewById(R.id.txtPerson);
    TextView value = v.findViewById(R.id.txtValue);
    TextView description = v.findViewById(R.id.txtDescription);
    TextView installment = v.findViewById(R.id.txtInstallment);

    Transaction reg = transactions.get(i);

    person.setText(reg.getPerson());
    value.setText(reg.getValue());
    description.setText(reg.getDescription());
    installment.setText(reg.getInstallment());

    return v;
  }

}