package ru.calloop.pikabu_demo.mainActivity;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.App;
import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;
import ru.calloop.pikabu_demo.services.PostItemRepository;

public class MainActivity extends AppCompatActivity implements MainContract.IView {
    private MainPresenter mainPresenter;
    private AppBarConfiguration appBarConfiguration;
//    private BlocksListMainAdapter adapter;

    List<PostItem> postItems;

//    @Inject
//    PostItemRepository postItemRepository;
//
//    //@Inject
//    PostItemModel postItemModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        PikabuDB database = Room.databaseBuilder(getApplicationContext(), PikabuDB.class, "Pikabu").allowMainThreadQueries().build();
        postItems = new ArrayList<>();
        postItems.add(new PostItem(1, 1, 1, 1, "lolo"));

        Bundle bundle = new Bundle();
        bundle.putSerializable("postItems", postItems);
// set Fragmentclass Arguments
        Tab1MainFragment fragment = new Tab1MainFragment();
        fragment.setArguments(bundle);

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.nav_host_fragment, fragment).commit();

//        DaggerAppComponent.builder()
//                .appModule(new AppModule(getApplication()))
//                .roomModule(new RoomModule(getApplication()))
//                .build()
//                .inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.home_fragment, R.id.settings_fragment)
                .setOpenableLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(MainActivity.this,
                navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setPresenter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    private void setPresenter() {
        //adapter = new BlocksListMainAdapter(postItems);
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
//            Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
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