package ru.calloop.pikabu_demo.ui.mainActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import ru.calloop.pikabu_demo.R;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {

    Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    NavHostFragment navHostFragment;
    NavController navController;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    FragmentManager fragmentManager;
//    @Inject
//    PostItemRepository postItemRepository;
//
//    //@Inject
//    PostItemModel postItemModel;

//    private OnAboutDataReceivedListener mAboutDataListener;
//
//    public interface OnAboutDataReceivedListener {
//        void onDataReceived(List<PostItem> postItemList);
//    }
//
//    public void setAboutDataListener(OnAboutDataReceivedListener listener) {
//        this.mAboutDataListener = listener;
//    }

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
        NavController navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration
                .Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(MainActivity.this,
                navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        if (savedInstanceState == null) {
//            MainFragment fragment = new MainFragment();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.activity_navigation_controller, fragment)
//                    .commit();
//        }


//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

//        DaggerAppComponent.builder()
//                .appModule(new AppModule(getApplication()))
//                .roomModule(new RoomModule(getApplication()))
//                .build()
//                .inject(this);

//        toolbar = findViewById(R.id.toolbar_main);
//        setSupportActionBar(toolbar);

//        navHostFragment =
//                (NavHostFragment) getSupportFragmentManager()
//                        .findFragmentById(R.id.main_navigation_controller);
//        if (navHostFragment != null) {
//            navController = navHostFragment.getNavController();
//
//            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                    R.navigation.main_navigation)
//                    .build();
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//            NavigationUI.setupWithNavController(toolbar, navController);

//            appBarConfiguration = new AppBarConfiguration.Builder(R.id.main_graph).build();
//            navController.setGraph(R.navigation.main_navigation);
//            navController.addOnDestinationChangedListener(this);
//        }
//
//        NavigationUI.setupWithNavController(toolbar, navController);
//        NavigationUI.setupActionBarWithNavController(this,
//                navController, appBarConfiguration);


//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_container_main, new MainFragment().newInstance())
//                .addToBackStack("BaseFragments")
//                .commit();

        setPresenter();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_navigation_controller);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (item.getItemId() == R.id.homeFragment) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        return NavigationUI.navigateUp(navController, drawerLayout)
//                || super.onSupportNavigateUp();
//    }

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