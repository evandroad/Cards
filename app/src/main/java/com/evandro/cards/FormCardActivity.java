package com.evandro.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.evandro.cards.core.TabsAdapterCards;
import com.evandro.cards.fragments.InsertCardsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FormCardActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_card);

    TabLayout tabLayout = findViewById(R.id.tabsCard);
    ViewPager2 viewPager = findViewById(R.id.viewpagerCard);

    TabsAdapterCards tabsAdapterCards = new TabsAdapterCards(this);
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

    viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);

        if (position == 0) {
          InsertCardsFragment insertFragment =
            (InsertCardsFragment) tabsAdapterCards.createFragment(position);
          insertFragment.onFragmentVisible();
        } else if (position == 1) {
          TabsAdapterCards.AlterarFragment alterarFragment =
            (TabsAdapterCards.AlterarFragment) tabsAdapterCards.createFragment(position);
          if (alterarFragment != null) {
            alterarFragment.onFragmentVisible();
          }
        } else if (position == 2) {
          TabsAdapterCards.ApagarFragment apagarFragment =
            (TabsAdapterCards.ApagarFragment) tabsAdapterCards.createFragment(position);
          if (apagarFragment != null) {
            apagarFragment.onFragmentVisible();
          }
        }
      }
    });
  }


}