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
import com.evandro.cards.dao.DescriptionDAO;

public class InsertDescriptionsFragment extends Fragment {

  EditText txtDescription;
  ProgressDialog progressDialog;
  DescriptionDAO descriptionDAO;
  Globally globally = Globally.getInstance();

  public InsertDescriptionsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_insert_descriptions, container, false);

    descriptionDAO = new DescriptionDAO(requireContext());
    txtDescription = view.findViewById(R.id.txtDescription);
    Button btnInsert = view.findViewById(R.id.btnInsertDescription);

    btnInsert.setOnClickListener(v -> insert());

    return view;
  }

  private void insert() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    descriptionDAO.inserir(txtDescription.getText().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Descrição inserida com sucesso").show();
    globally.getListFromDB(Globally.DESCRIPTION, null, requireContext());

    txtDescription.setText("");
    txtDescription.requestFocus();
  }

  private boolean checkFields() {
    if (txtDescription.getText().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Preencha a descrição.").show();
      return true;
    }

    return false;
  }

}