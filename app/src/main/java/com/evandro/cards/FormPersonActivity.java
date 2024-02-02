package com.evandro.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.evandro.cards.core.TabsAdapterCards;
import com.evandro.cards.core.TabsAdapterPeople;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FormPersonActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_person);

    TabLayout tabLayout = findViewById(R.id.tabsPerson);
    ViewPager2 viewPager = findViewById(R.id.viewpagerPerson);

    TabsAdapterPeople tabsAdapterPeople = new TabsAdapterPeople(this);
    viewPager.setAdapter(tabsAdapterPeople);

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

}