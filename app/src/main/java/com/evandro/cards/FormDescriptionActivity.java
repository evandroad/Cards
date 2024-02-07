package com.evandro.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.evandro.cards.core.TabsAdapterDescriptions;
import com.evandro.cards.fragments.DeleteDescriptionsFragment;
import com.evandro.cards.fragments.UpdateDescriptionsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FormDescriptionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_description);

    TabLayout tabLayout = findViewById(R.id.tabsDescription);
    ViewPager2 viewPager = findViewById(R.id.viewpagerDescription);

    TabsAdapterDescriptions tabsAdapterDescriptions = new TabsAdapterDescriptions(this);
    viewPager.setAdapter(tabsAdapterDescriptions);

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

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + position);

        if (fragment instanceof UpdateDescriptionsFragment) {
          ((UpdateDescriptionsFragment) fragment).onFragmentVisible();
        } else if (fragment instanceof DeleteDescriptionsFragment) {
          ((DeleteDescriptionsFragment) fragment).onFragmentVisible();
        }
      }
    });
  }

}