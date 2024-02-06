package com.evandro.cards.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.evandro.cards.R;
import com.evandro.cards.core.Globally;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateCardsFragment extends Fragment {

  Spinner spCurrentCard;
  EditText txtNewCard;
  private boolean isVisibleToUser;
  Globally globally = Globally.getInstance();

  public UpdateCardsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_update_cards, container, false);

    spCurrentCard = view.findViewById(R.id.spCurrentCard);
    txtNewCard = view.findViewById(R.id.txtNewCard);
    Button btnUpdateCard = view.findViewById(R.id.btnUpdateCard);

//    txtValue.addTextChangedListener(new MoneyTextWatcher(txtValue));

    ArrayAdapter<String> adapter = new ArrayAdapter<>(
      getContext(),
      R.layout.item_spinner,
      R.id.txtSpinner,
      globally.getCards()
    );
    spCurrentCard.setAdapter(adapter);

    btnUpdateCard.setOnClickListener(v -> crud());

    return view;
  }

  private void crud() {

  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);

    this.isVisibleToUser = isVisibleToUser;

    if (isVisibleToUser) {
//      onFragmentVisible();
      Log.i("Evandro", "Está visivel");
    } else {
      Log.i("Evandro", "NÃO está visivel");
    }
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);

    if (!hidden) {
      Log.i("Evandro", "Está visivel");
    } else {
      Log.i("Evandro", "NÃO está visivel");
    }
  }

}