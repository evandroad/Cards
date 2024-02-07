package com.evandro.cards.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.evandro.cards.R;
import com.evandro.cards.core.Alert;
import com.evandro.cards.core.Globally;
import com.evandro.cards.core.Util;
import com.evandro.cards.dao.CardDAO;
import com.evandro.cards.dao.PersonDAO;

public class DeletePeopleFragment extends Fragment {

  Spinner spPerson;
  ProgressDialog progressDialog;
  PersonDAO personDAO;
  Globally globally = Globally.getInstance();

  public DeletePeopleFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_delete_people, container, false);

    personDAO = new PersonDAO(requireContext());
    spPerson = view.findViewById(R.id.spPerson);
    Button btnDeletePerson = view.findViewById(R.id.btnDeletePerson);

    Util.fillSpinner(requireContext(), globally.getPeople(), spPerson);
    btnDeletePerson.setOnClickListener(v -> delete());

    return view;
  }

  private void delete() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    personDAO.delete(spPerson.getSelectedItem().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Cartão apagado com sucesso").show();
    globally.getListFromDB(Globally.PERSON, spPerson, requireContext());

    spPerson.setSelection(0);
    spPerson.requestFocus();
  }

  private boolean checkFields() {
    if (spPerson.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Selecione uma pessoa.").show();
      return true;
    }

    return false;
  }

  public void onFragmentVisible() { Util.fillSpinner(requireContext(), globally.getPeople(), spPerson); }

}