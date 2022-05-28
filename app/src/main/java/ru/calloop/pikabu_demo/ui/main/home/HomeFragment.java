package ru.calloop.pikabu_demo.ui.main.home;

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

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.BaseFragment;
import ru.calloop.pikabu_demo.ui.main.adapters.HomeAdapter;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.ISessionPreferenceRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.SessionPreferenceRepository;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private AppCompatActivity activity;
    private NavController navController;
    private Toolbar toolbar;

    private ISessionPreferenceRepository sessionPreferenceRepository;
    private HomeAdapter homeAdapter;
    private ViewPager2 viewPager;

    private String[] tabTitles = {"ГОРЯЧЕЕ", "ЛУЧШЕЕ", "СВЕЖЕЕ", "МОЯ ЛЕНТА"};

    @Override
    public BaseFragment fragment() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View fragmentView(LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        activity = (AppCompatActivity) getActivity();
        assert activity != null;
        toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);

        homeAdapter = new HomeAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(homeAdapter);

        sessionPreferenceRepository = new SessionPreferenceRepository(activity);

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
            if (sessionPreferenceRepository.sessionStarted()) {
                navController.navigate(R.id.action_homeFragment_to_createPostFragment);
            } else navController.navigate(R.id.action_homeFragment_to_signInFragment);
        }
    }
}