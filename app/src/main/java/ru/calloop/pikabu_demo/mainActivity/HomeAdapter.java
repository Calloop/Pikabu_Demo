package ru.calloop.pikabu_demo.mainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeAdapter extends FragmentStateAdapter {
    public HomeAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return Tab1MainFragment.newInstance(0, "Page # 1");
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