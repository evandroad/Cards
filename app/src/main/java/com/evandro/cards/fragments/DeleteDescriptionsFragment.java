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
import com.evandro.cards.dao.DescriptionDAO;

public class DeleteDescriptionsFragment extends Fragment {

  Spinner spDescription;
  ProgressDialog progressDialog;
  DescriptionDAO descriptionDAO;
  Globally globally = Globally.getInstance();

  public DeleteDescriptionsFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_delete_descriptions, container, false);

    descriptionDAO = new DescriptionDAO(requireContext());
    spDescription = view.findViewById(R.id.spDescription);
    Button btnDeleteDescription = view.findViewById(R.id.btnDeleteDescription);

    Util.fillSpinner(requireContext(), globally.getDescriptions(), spDescription);
    btnDeleteDescription.setOnClickListener(v -> delete());

    return view;
  }

  private void delete() {
    if (checkFields()) {
      return;
    }

    progressDialog = Alert.alertLoading(getContext());
    progressDialog.show();

    descriptionDAO.delete(spDescription.getSelectedItem().toString());
    progressDialog.dismiss();
    Alert.alertSuccess(getContext(), "Cartão apagado com sucesso").show();
    globally.getListFromDB(Globally.DESCRIPTION, spDescription, requireContext());

    spDescription.setSelection(0);
    spDescription.requestFocus();
  }

  private boolean checkFields() {
    if (spDescription.getSelectedItem().toString().isEmpty()) {
      Alert.alertInfo(getContext(), "Selecione uma descrição.").show();
      return true;
    }

    return false;
  }

  public void onFragmentVisible() { Util.fillSpinner(requireContext(), globally.getDescriptions(), spDescription); }

}