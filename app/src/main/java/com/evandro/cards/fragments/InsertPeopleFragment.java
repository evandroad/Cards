package com.evandro.cards.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.evandro.cards.R;
import com.evandro.cards.core.Alert;
import com.evandro.cards.core.Globally;
import com.evandro.cards.dao.PersonDAO;

public class InsertPeopleFragment extends Fragment {

  EditText txtPerson;
  ProgressDialog progressDialog;
  PersonDAO personDAO;
  Globally globally = Globally.getInstance();

  public InsertPeopleFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_insert_people, container, false);

    personDAO = new PersonDAO(requireContext());
    txtPerson = view.findViewById(R.id.txtPerson);
    Button btnInsert = view.findViewById(R.id.btnInsertPerson);

    btnInsert.setOnClickListener(v -> insert());

    return view;
  }

  private void insert() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    personDAO.insert(txtPerson.getText().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Pessoa inserida com sucesso").show();
    globally.getListFromDB(Globally.PERSON, null, requireContext());

    txtPerson.setText("");
    txtPerson.requestFocus();
  }

  private boolean checkFields() {
    if (txtPerson.getText().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Preencha a pessoa.").show();
      return true;
    }

    return false;
  }

}