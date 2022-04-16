package ru.calloop.pikabu_demo.ui.main.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import ru.calloop.pikabu_demo.ui.signing.models.SessionManager;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.main.mainActivity.adapters.HomeAdapter;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Toolbar toolbar;
    private AppCompatActivity activity;
    private NavController navController;

    private HomeAdapter homeAdapter;
    private ViewPager2 viewPager;

    private String[] tabTitles = {"ГОРЯЧЕЕ", "ЛУЧШЕЕ", "СВЕЖЕЕ", "МОЯ ЛЕНТА"};

    @Override
    public BaseFragment providerFragment() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        activity = (AppCompatActivity) getActivity();
        assert activity != null;
        toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);

        homeAdapter = new HomeAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(homeAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(tabTitles[position])).attach();

        FloatingActionButton fab = view.findViewById(R.id.fab_home_to_create_post);

        NavHostFragment navHostFragment = (NavHostFragment) activity
                .getSupportFragmentManager().findFragmentById(R.id.activity_navigation_controller);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.fab_home_to_create_post) {
            SharedPreferences sharedPreferences = requireContext()
                    .getSharedPreferences(SessionManager.KEY, Context.MODE_PRIVATE);
            if (sharedPreferences.contains(SessionManager.AUTHORIZED)){
                navController.navigate(R.id.action_homeFragment_to_createPostFragment);
            } else {
                navController.navigate(R.id.action_homeFragment_to_signInFragment);
            }

        }
    }
}