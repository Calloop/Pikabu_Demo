package ru.calloop.pikabu_demo.ui.mainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.signingActivity.models.SessionManager;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar toolbar;
    private NavController navController;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    private Button buttonDrawerSignIn, buttonDrawerSignOut;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        toolbar = findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_navigation_controller);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        navController.setGraph(R.navigation.activity_navigation);

        appBarConfiguration = new AppBarConfiguration
                .Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(MainActivity.this,
                navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerLayout = navigationView.getHeaderView(0);
        buttonDrawerSignIn = headerLayout.findViewById(R.id.buttonDrawerSignIn);
        buttonDrawerSignOut = headerLayout.findViewById(R.id.buttonDrawerSignOut);

        buttonDrawerSignIn.setOnClickListener(this);
        buttonDrawerSignOut.setOnClickListener(this);

        setPresenter();
        onUpdate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onUpdate();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buttonDrawerSignIn) {
            navController.navigate(R.id.action_homeFragment_to_signInFragment);
        } else if (id == R.id.buttonDrawerSignOut) {
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.endUserSession();
            onUpdate();
        }
    }

    private void onUpdate() {
        sharedPreferences = getSharedPreferences(SessionManager.KEY, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(SessionManager.AUTHORIZED)) {
            buttonDrawerSignIn.setVisibility(View.GONE);
            buttonDrawerSignOut.setVisibility(View.VISIBLE);
        } else {
            buttonDrawerSignIn.setVisibility(View.VISIBLE);
            buttonDrawerSignOut.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation
                .findNavController(this, R.id.activity_navigation_controller);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.activity_navigation_controller);
//        assert navHostFragment != null;
//        NavController navController = navHostFragment.getNavController();
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (item.getItemId() == R.id.homeFragment) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setPresenter() {

//        adapter = new BlocksListMainAdapter(postItems);
//        postItemModel = new ViewModelProvider(this).get(PostItemModel.class);
//        postItemModel.getAll().observe(this, posts -> adapter.setData(posts));

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//        RecyclerView recyclerView = findViewById(R.id.list_main);
//        //recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

        //PostItemDbHelper postItemDbHelper = new PostItemDbHelper(this);
        //PostItemModel model = new PostItemModel(postItemDbHelper);
        //mainPresenter = new MainPresenter(model);
        //mainPresenter.attachView(this);
        //mainPresenter.viewIsReady();
    }
}

//region [OLD]
//public class MainActivity extends AppCompatActivity {
//
//    Button buttonSignIn;
//    SessionManager sessionManager;
//
//    private AppBarConfiguration mAppBarConfiguration;
//
//    TabLayout tabLayout;
//    ViewPager2 viewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ru.calloop.pikabu_demo.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.appBarMain.toolbarMain);
//        binding.appBarMain.fab.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CreatePostFragment.class);
//            startActivity(intent);
//        });
//
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_settings).setOpenableLayout(drawer).build();
//
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_container_view_content_main);
//        assert navHostFragment != null;
//        NavController navController = navHostFragment.getNavController();
//        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//        //
//        viewPager = findViewById(R.id.pager);
//        tabLayout = findViewById(R.id.tabLayout);
//
//        viewPager.setAdapter(createViewAdapter());
//        new TabLayoutMediator(tabLayout, viewPager,
//                (tab, position) -> tab.setText("Tab " + (position + 1))).attach();
//
//        sessionManager = new SessionManager(getApplicationContext());
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_container_view_content_main);
//        assert navHostFragment != null;
//        NavController navController = navHostFragment.getNavController();
//        //NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//
//    }
//
//    private ViewPagerAdapter createViewAdapter() {
//        return new ViewPagerAdapter(this);
//    }
//
////        if (sessionManager.getLogin() == true)
////        {
////            buttonSignIn.setText(sessionManager.getUsername());
////            imageButtonCreatePost.setVisibility(View.VISIBLE);
////            Toast.makeText(MainActivity.this, "Пользователь " + sessionManager.getUsername(), Toast.LENGTH_SHORT).show();
////        } else
////        {
////            imageButtonCreatePost.setVisibility(View.GONE);
////            Toast.makeText(MainActivity.this, "Пользователь не авторизован", Toast.LENGTH_SHORT).show();
////        }
//
////        buttonSignIn.setOnClickListener(v -> {
////            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
////            startActivity(intent);
////        });
//}
//endregion