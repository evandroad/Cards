package com.evandro.cards;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAdapterCards extends FragmentStateAdapter {

  public TabsAdapterCards(@NonNull FragmentActivity fragmentActivity) { super(fragmentActivity); }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 0:
        return new InsertCardsFragment();
      case 1:
        return new UpdateCardsFragment();
      case 2:
        return new DeleteCardsFragment();
    }

    return new Fragment();
  }

  @Override
  public int getItemCount() { return 3; }

}