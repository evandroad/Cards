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
import com.evandro.cards.dao.PersonDAO;

public class UpdatePeopleFragment extends Fragment {

  Spinner spCurrentPerson;
  EditText txtNewPerson;
  ProgressDialog progressDialog;
  PersonDAO personDAO;
  Globally globally = Globally.getInstance();

  public UpdatePeopleFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_update_people, container, false);

    personDAO = new PersonDAO(requireContext());
    spCurrentPerson = view.findViewById(R.id.spCurrentPerson);
    txtNewPerson = view.findViewById(R.id.txtNewPerson);
    Button btnUpdatePerson = view.findViewById(R.id.btnUpdatePerson);

    Util.fillSpinner(requireContext(), globally.getPeople(), spCurrentPerson);
    btnUpdatePerson.setOnClickListener(v -> update());
    globally.getListFromDB(Globally.PERSON, null, getContext());

    return view;
  }

  private void update() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    personDAO.update(spCurrentPerson.getSelectedItem().toString(), txtNewPerson.getText().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(requireContext(), "Pessoa alterada com sucesso").show();
    globally.getListFromDB(Globally.PERSON, spCurrentPerson, requireContext());

    spCurrentPerson.setSelection(0);
    txtNewPerson.setText("");
    txtNewPerson.clearFocus();
  }

  private boolean checkFields() {
    if (spCurrentPerson.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Selecione um cartão.").show();
      return true;
    }

    if (txtNewPerson.getText().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Preencha o cartão.").show();
      return true;
    }

    return false;
  }

  public void onFragmentVisible() { Util.fillSpinner(requireContext(), globally.getPeople(), spCurrentPerson); }

}