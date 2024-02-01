package com.evandro.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.evandro.cards.databinding.FragmentSecondBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SecondFragment extends Fragment {

  private FragmentSecondBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSecondBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    TabLayout tabLayout = view.findViewById(R.id.tabs);
    ViewPager2 viewPager = view.findViewById(R.id.viewpagerCommon);

    TabsAdapterCards tabsAdapterCards = new TabsAdapterCards(requireActivity());
    viewPager.setAdapter(tabsAdapterCards);

    new TabLayoutMediator(tabLayout, viewPager,
        (tab, position) -> {
          switch (position) {
            case 0:
              tab.setText("Cadastrar");
              break;
            case 1:
              tab.setText("Alterar");
              break;
            case 2:
              tab.setText("Apagar");
              break;
          }
        }).attach();
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

}