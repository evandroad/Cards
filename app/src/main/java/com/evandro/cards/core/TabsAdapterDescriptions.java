package com.evandro.cards.core;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.evandro.cards.fragments.DeleteDescriptionsFragment;
import com.evandro.cards.fragments.DeletePeopleFragment;
import com.evandro.cards.fragments.InsertDescriptionsFragment;
import com.evandro.cards.fragments.InsertPeopleFragment;
import com.evandro.cards.fragments.UpdateDescriptionsFragment;
import com.evandro.cards.fragments.UpdatePeopleFragment;

public class TabsAdapterDescriptions extends FragmentStateAdapter {

  public TabsAdapterDescriptions(@NonNull FragmentActivity fragmentActivity) { super(fragmentActivity); }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 0:
        return new InsertDescriptionsFragment();
      case 1:
        return new UpdateDescriptionsFragment();
      case 2:
        return new DeleteDescriptionsFragment();
    }

    return new Fragment();
  }

  @Override
  public int getItemCount() { return 3; }

}
