package com.evandro.cards.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.evandro.cards.R;
import com.evandro.cards.core.Alert;
import com.evandro.cards.core.Globally;
import com.evandro.cards.core.Util;
import com.evandro.cards.dao.CardDAO;

public class UpdateCardsFragment extends Fragment {

  Spinner spCurrentCard;
  EditText txtNewCard;
  ProgressDialog progressDialog;
  CardDAO cardDAO;
  Globally globally = Globally.getInstance();

  public UpdateCardsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_update_cards, container, false);

    cardDAO = new CardDAO(requireContext());
    spCurrentCard = view.findViewById(R.id.spCurrentCard);
    txtNewCard = view.findViewById(R.id.txtNewCard);
    Button btnUpdateCard = view.findViewById(R.id.btnUpdateCard);

    Util.fillSpinner(requireContext(), globally.getCards(), spCurrentCard);
    btnUpdateCard.setOnClickListener(v -> update());
    globally.getListFromDB(Globally.CARD, null, getContext());

    return view;
  }

  private void update() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    cardDAO.update(spCurrentCard.getSelectedItem().toString(), txtNewCard.getText().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Cartão alterado com sucesso").show();
    globally.getListFromDB(Globally.CARD, spCurrentCard, requireContext());

    spCurrentCard.setSelection(0);
    txtNewCard.setText("");
    txtNewCard.clearFocus();
  }

  private boolean checkFields() {
    if (spCurrentCard.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Selecione um cartão.").show();
      return true;
    }

    if (txtNewCard.getText().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Preencha o cartão.").show();
      return true;
    }

    return false;
  }

  public void onFragmentVisible() { Util.fillSpinner(requireContext(), globally.getCards(), spCurrentCard); }

}