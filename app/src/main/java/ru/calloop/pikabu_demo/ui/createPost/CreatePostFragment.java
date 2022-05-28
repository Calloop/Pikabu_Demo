package ru.calloop.pikabu_demo.ui.createPost;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistryOwner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.BaseFragment;
import ru.calloop.pikabu_demo.ui.createPost.adapters.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.IPreferenceRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.ISessionPreferenceRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.PreferenceRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.SessionPreferenceRepository;

public class CreatePostFragment extends BaseFragment {

    private IPreferenceRepository preferenceRepository;
    private ISessionPreferenceRepository sessionPreferenceRepository;
    private CreatePostViewModel createPostViewModel;
    private BlocksListCreatePostAdapter adapter;

    private AppCompatActivity activity;
    private NavController navController;
    private RecyclerView recyclerView;
    private TextView textViewDescriptionCreatePost;
    private TextInputEditText createPostHeadline;
    private ActionMode actionMode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public BaseFragment fragment() {
        return newInstance();
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        activity = (AppCompatActivity) requireActivity();
        recyclerView = view.findViewById(R.id.list_create_post);
        textViewDescriptionCreatePost = view.findViewById(R.id.textView_description_create_post);
        createPostHeadline = view.findViewById(R.id.create_post_headline_text);

        preferenceRepository = new PreferenceRepository(activity);
        sessionPreferenceRepository = new SessionPreferenceRepository(activity);
        createPostViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        adapter = new BlocksListCreatePostAdapter();

        NavHostFragment navHostFragment = (NavHostFragment) activity
                .getSupportFragmentManager().findFragmentById(R.id.activity_navigation_controller);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        loadPreference();
        setToolbar();
        setAdapter();

        getChildFragmentManager()
                .setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
                    int result = bundle.getInt("bundleKey");
                    createPostItem(result);
                });

        listIsEmpty();

        return view;
    }

    private void loadPreference() {
//        adapter.updateList(preferenceRepository.getPostItems());

        createPostViewModel.getPostItems().observe(getViewLifecycleOwner(), postItems -> adapter.updateList(postItems));

//        createPostViewModel
//        if (createPostViewModel.getPostItems().size() != 0)
//        {
//            adapter.updateList(createPostViewModel.getPostItems());
//        }
        createPostHeadline.setText(preferenceRepository.getPostHealdine());
        createPostHeadline.addTextChangedListener(new TextWatcher() {
            private Timer timer = new Timer();
            private final long DELAY = 200;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        preferenceRepository.setPostHeadline(editable.toString());
                    }
                }, DELAY);
            }
        });
    }

    //region [SET: TOOLBAR, FRAGMENT MANAGER, RECYCLER VIEW]
    private void setToolbar() {
        Toolbar toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);
    }

    private void setAdapter() {
//        presenter = new CreatePostPresenter();
//        presenter.attachView(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
//        createPostViewModel.getLocalPostItemList()
//                .observe(activity,
//                        postItems -> {
//                            adapter = new BlocksListCreatePostAdapter(postItems);
//                            recyclerView.setAdapter(adapter);
//                        });

        //Log.d("TEST", "" + adapter.getItemCount());
    }
    //endregion

    //region [TOOLBAR OPTION MENU]
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_create_post, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.edit_create_post) {
            if (actionMode != null) {
                return false;
            }

            activity.startSupportActionMode(actionModeCallback);
            //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (itemId == R.id.add_post_create_post) {
            int userId = sessionPreferenceRepository.getAccountId();
            String postHeadline = Objects.requireNonNull(createPostHeadline.getText()).toString();
            createPostViewModel.setPostItems(adapter.getAdapterList());
            createPostViewModel.insertPostToDB(userId, postHeadline);
            preferenceRepository.clearPreference();
            navController.popBackStack(R.id.homeFragment, false);
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region [ACTION MODE]
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            adapter.editModeIsActive(true);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.apply_edit_contextual_create_post) {
                adapter.clearPreparedToDeleteItemList();
                preferenceRepository.setPostItems(adapter.getAdapterList());
                showToast("EDITING APPLIED");
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            adapter.editModeIsActive(false);
            if (adapter.getPrepareToDeleteItemsList().size() > 0) {
                adapter.getAdapterList().addAll(adapter.getPrepareToDeleteItemsList());
                adapter.getAdapterList().sort(Comparator.comparing(PostItem::getPosition));
            }
            adapter.clearPreparedToDeleteItemList();
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        }
    };
    //endregion

    //region [SHOW EVENTS]
    public void showPostItems(List<PostItem> postItems) {
        //adapter.loadPostItems(postItems);
    }

    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region [CLICK EVENTS]
    private void createPostItem(int type) {
        if (type != 0) {
//            List<PostItem> test = new ArrayList<>(1);
//            PostItem postItem = new PostItem(0, type, null);
//            test.add(postItem);
//     //       Log.d("TEST", "" + adapter.getItemCount());
//            CreatePostDiffUtilCallback createPostDiffUtilCallback =
//                    new CreatePostDiffUtilCallback(adapter.getAdapterList(),
//                            test);
//            DiffUtil.DiffResult diffResult =
//                    DiffUtil.calculateDiff(createPostDiffUtilCallback);
//
//            adapter.createPostItem(type);
//            diffResult.dispatchUpdatesTo(adapter);
//            listIsEmpty();

            adapter.createPostItem(type);
            //createPostViewModel.setPostItems(adapter.getAdapterList());
            listIsEmpty();

        }
    }
    //endregion

    //region [OPERATIONS]
    public void listIsEmpty() {
        if (adapter.getItemCount() == 0) {
            textViewDescriptionCreatePost.setVisibility(View.VISIBLE);
        } else
            textViewDescriptionCreatePost.setVisibility(View.GONE);
    }

    public void checkEditMode() {
        // скрыть кнопки редактирования
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (adapter.getItemCount() != 0) {
//            adapter.updateList(createPostViewModel.getPostItes());
//        }
    }

    @Override
    public void onDestroy() {
        //presenter.detachView();
        super.onDestroy();
    }
    //endregion
}