package ru.calloop.pikabu_demo.adapters.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import ru.calloop.pikabu_demo.ui.fragments.main.home.tabs.Tab1MainFragment;

public class PostTabsAdapter extends FragmentStateAdapter {

    public PostTabsAdapter(Fragment fragment) {
        super(fragment);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new Tab1MainFragment();
        } else if (position == 1) {
            return new Tab1MainFragment();
        } else if (position == 2) {
            return new Tab1MainFragment();
        } else if (position == 3) {
            return new Tab1MainFragment();
        } else return new Tab1MainFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}