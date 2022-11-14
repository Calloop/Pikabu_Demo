package ru.calloop.pikabu_demo.ui.fragments.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.tabs.TabLayoutMediator;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.HomeBinding;
import ru.calloop.pikabu_demo.adapters.main.PostTabsAdapter;
import ru.calloop.pikabu_demo.data.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.data.repositories.SharedPreferences.session.SessionPreferences;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private AppCompatActivity activity;
    private NavController navController;
    private ISessionPreferences sessionPreferenceRepository;

    private final String[] tabTitles = {"ГОРЯЧЕЕ", "ЛУЧШЕЕ", "СВЕЖЕЕ", "МОЯ ЛЕНТА"};

    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeBinding homeBinding = HomeBinding.inflate(inflater, container, false);

        activity = (AppCompatActivity) requireActivity();
        activity.addMenuProvider(
                new menuProvider(), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        navController = NavHostFragment.findNavController(this);

        PostTabsAdapter postTabsAdapter = new PostTabsAdapter(this);
        homeBinding.pager.setAdapter(postTabsAdapter);
        homeBinding.fabHomeToCreatePost.setOnClickListener(this);

        new TabLayoutMediator(homeBinding.tabLayout, homeBinding.pager, (tab, position) ->
                tab.setText(tabTitles[position])).attach();
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sessionPreferenceRepository = new SessionPreferences(activity);
    }

    private static class menuProvider implements MenuProvider {
        @Override
        public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.toolbar_home, menu);
        }

        @Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();

            return false;
        }
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();

        if (id == R.id.fab_home_to_create_post) {
            if (sessionPreferenceRepository.sessionStarted()) {
                navController.navigate(R.id.action_homeFragment_to_createPostFragment);
            } else navController.navigate(R.id.action_homeFragment_to_signInFragment);
        }
    }
}