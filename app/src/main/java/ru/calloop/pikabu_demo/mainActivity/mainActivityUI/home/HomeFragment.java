package ru.calloop.pikabu_demo.mainActivity.mainActivityUI.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostActivity;
import ru.calloop.pikabu_demo.mainActivity.HomeAdapter;

public class HomeFragment extends Fragment {

    HomeAdapter homeAdapter;
    ViewPager2 viewPager;

    String[] tabTtiles = {"ГОРЯЧЕЕ", "ЛУЧШЕЕ", "СВЕЖЕЕ", "МОЯ ЛЕНТА"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homeAdapter = new HomeAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(homeAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(tabTtiles[position])).attach();

        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreatePostActivity.class);
            startActivity(intent);
        });
    }
}