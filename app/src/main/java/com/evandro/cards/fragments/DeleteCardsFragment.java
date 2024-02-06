package com.evandro.cards.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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

public class DeleteCardsFragment extends Fragment {

  Spinner spCard;
  ProgressDialog progressDialog;
  CardDAO cardDAO;
  Globally globally = Globally.getInstance();

  public DeleteCardsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_delete_cards, container, false);

    cardDAO = new CardDAO(requireContext());
    spCard = view.findViewById(R.id.spCard);
    Button btnUpdateCard = view.findViewById(R.id.btnDeleteCard);

    Util.fillSpinner(getContext(), globally.getCards(), spCard);
    btnUpdateCard.setOnClickListener(v -> delete());

    return view;
  }

  private void delete() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    cardDAO.delete(spCard.getSelectedItem().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Cartão apagado com sucesso").show();
    globally.getListFromDB(Globally.CARD, spCard, requireContext());

    spCard.setSelection(0);
    spCard.requestFocus();
  }

  private boolean checkFields() {
    if (spCard.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Selecione um cartão.").show();
      return true;
    }

    return false;
  }

  public void onFragmentVisible() { Util.fillSpinner(requireContext(), globally.getCards(), spCard); }

}