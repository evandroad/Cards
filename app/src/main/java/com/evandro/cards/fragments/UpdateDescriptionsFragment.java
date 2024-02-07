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
import com.evandro.cards.dao.DescriptionDAO;

public class UpdateDescriptionsFragment extends Fragment {

  Spinner spCurrentDescription;
  EditText txtNewDescription;
  ProgressDialog progressDialog;
  DescriptionDAO descriptionDAO;
  Globally globally = Globally.getInstance();

  public UpdateDescriptionsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_update_descriptions, container, false);

    descriptionDAO = new DescriptionDAO(requireContext());
    spCurrentDescription = view.findViewById(R.id.spCurrentDescription);
    txtNewDescription = view.findViewById(R.id.txtNewDescription);
    Button btnUpdateDescription = view.findViewById(R.id.btnUpdateDescription);

    Util.fillSpinner(requireContext(), globally.getDescriptions(), spCurrentDescription);
    btnUpdateDescription.setOnClickListener(v -> update());
    globally.getListFromDB(Globally.DESCRIPTION, null, requireContext());

    return view;
  }

  private void update() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    descriptionDAO.update(spCurrentDescription.getSelectedItem().toString(), txtNewDescription.getText().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Cartão alterado com sucesso").show();
    globally.getListFromDB(Globally.DESCRIPTION, spCurrentDescription, requireContext());

    spCurrentDescription.setSelection(0);
    txtNewDescription.setText("");
    txtNewDescription.clearFocus();
  }

  private boolean checkFields() {
    if (spCurrentDescription.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Selecione um cartão.").show();
      return true;
    }

    if (txtNewDescription.getText().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Preencha o cartão.").show();
      return true;
    }

    return false;
  }

  public void onFragmentVisible() { Util.fillSpinner(requireContext(), globally.getDescriptions(), spCurrentDescription); }

}