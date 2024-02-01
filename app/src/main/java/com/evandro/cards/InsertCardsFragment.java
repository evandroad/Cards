package com.evandro.cards;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InsertCardsFragment extends Fragment {

  EditText txtCard;
  ProgressDialog progressDialog;
  CardDAO cardDAO;

  public InsertCardsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_insert_cards, container, false);

    cardDAO = new CardDAO(requireContext());
    txtCard = view.findViewById(R.id.txtCard);
    Button btnInsert = view.findViewById(R.id.btnInsertCard);

    btnInsert.setOnClickListener(v -> insert());

    return view;
  }

  private void insert() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    cardDAO.inserir(txtCard.getText().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Cartão inserido com sucesso").show();

    txtCard.setText("");
    txtCard.requestFocus();
  }

  private boolean checkFields() {
    if (txtCard.getText().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Preencha o cartão.").show();
      return true;
    }

    return false;
  }

}