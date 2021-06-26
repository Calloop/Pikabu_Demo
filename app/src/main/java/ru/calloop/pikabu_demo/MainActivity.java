package ru.calloop.pikabu_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import ru.calloop.pikabu_demo.adapter.SessionManager;
import ru.calloop.pikabu_demo.adapter.ViewPagerAdapter;
import ru.calloop.pikabu_demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button buttonSignIn;
    SessionManager sessionManager;

    private AppBarConfiguration mAppBarConfiguration;

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ru.calloop.pikabu_demo.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbarMain);
        binding.appBarMain.fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
            startActivity(intent);
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_settings).setOpenableLayout(drawer).build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_view_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(createViewAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("Tab " + (position + 1))).attach();

        sessionManager = new SessionManager(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_view_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        //NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    private ViewPagerAdapter createViewAdapter() {
        return new ViewPagerAdapter(this);
    }

//        if (sessionManager.getLogin() == true)
//        {
//            buttonSignIn.setText(sessionManager.getUsername());
//            imageButtonCreatePost.setVisibility(View.VISIBLE);
//            Toast.makeText(MainActivity.this, "Пользователь " + sessionManager.getUsername(), Toast.LENGTH_SHORT).show();
//        } else
//        {
//            imageButtonCreatePost.setVisibility(View.GONE);
//            Toast.makeText(MainActivity.this, "Пользователь не авторизован", Toast.LENGTH_SHORT).show();
//        }

//        buttonSignIn.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//            startActivity(intent);
//        });
}