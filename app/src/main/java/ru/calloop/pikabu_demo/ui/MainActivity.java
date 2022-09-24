package ru.calloop.pikabu_demo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.ActivityBinding;
import ru.calloop.pikabu_demo.databinding.ActivityHeaderDrawer2Binding;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.SessionPreferences;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ActivityBinding activityBinding;
    private ActivityHeaderDrawer2Binding headerBinding;
    private ISessionPreferences sessionPreferenceRepository;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionPreferenceRepository = new SessionPreferences(this);
        activityBinding = ActivityBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        setSupportActionBar(activityBinding.toolbarActivity);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(activityBinding.activityNavHostFragment.getId());
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupActionBarWithNavController(
                this, navController, activityBinding.drawerLayout);
        NavigationUI.setupWithNavController(activityBinding.navigationView, navController);

        View headerView = activityBinding.navigationView.getHeaderView(0);
        headerBinding = ActivityHeaderDrawer2Binding.bind(headerView);
        headerBinding.buttonDrawerSignIn.setOnClickListener(this);
        headerBinding.buttonDrawerSignOut.setOnClickListener(this);

        setSigningButtonVisibility();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSigningButtonVisibility();
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();

        if (id == headerBinding.buttonDrawerSignIn.getId()
                && Objects.requireNonNull(
                navController.getCurrentDestination()).getId() != R.id.signInFragment) {
            navController.navigate(R.id.action_homeFragment_to_signInFragment);
        } else if (id == headerBinding.buttonDrawerSignOut.getId()) {
            sessionPreferenceRepository.endUserSession();
        }
        setSigningButtonVisibility();
    }

    private void setSigningButtonVisibility() {
        if (sessionPreferenceRepository.sessionStarted()) {
            headerBinding.buttonDrawerSignIn.setVisibility(View.GONE);
            headerBinding.buttonDrawerSignOut.setVisibility(View.VISIBLE);
        } else {
            headerBinding.buttonDrawerSignIn.setVisibility(View.VISIBLE);
            headerBinding.buttonDrawerSignOut.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, activityBinding.drawerLayout);
    }
}