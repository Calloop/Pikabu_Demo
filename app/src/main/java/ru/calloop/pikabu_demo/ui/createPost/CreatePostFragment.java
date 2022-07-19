package ru.calloop.pikabu_demo.ui.createPost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;

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
    private TextView descriptionCreatePost, postHeadline;
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
    public View fragmentView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        activity = (AppCompatActivity) requireActivity();
        descriptionCreatePost = view.findViewById(R.id.textView_description_create_post);
        postHeadline = view.findViewById(R.id.create_post_headline_text);
        RecyclerView recyclerView = view.findViewById(R.id.list_create_post);
        Toolbar toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);

        preferenceRepository = new PreferenceRepository(activity);
        sessionPreferenceRepository = new SessionPreferenceRepository(activity);
        createPostViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        adapter = new BlocksListCreatePostAdapter();

        NavHostFragment navHostFragment = (NavHostFragment) activity
                .getSupportFragmentManager().findFragmentById(R.id.activity_navigation_controller);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        postHeadline.setText(preferenceRepository.getPostHealdine());
        adapter.updateList(preferenceRepository.getPostItems());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        getChildFragmentManager()
                .setFragmentResultListener("requestKey", this,
                        (requestKey, bundle) -> {
                            int result = bundle.getInt("bundleKey");
                            createPostItem(result);
                        });

        listIsEmpty();

        return view;
    }

    @Override
    public void onPause() {
        preferenceRepository.setPostHeadline(postHeadline.getText().toString());
        preferenceRepository.setPostItems(adapter.getAdapterList());
        super.onPause();
    }

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
            return true;
        }

        if (itemId == R.id.add_post_create_post) {
            int userId = sessionPreferenceRepository.getAccountId();
            createPostViewModel.insertPostToDB(userId, postHeadline.getText().toString(),
                    adapter.getAdapterList());
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
                Toast.makeText(activity, "EDITING APPLIED", Toast.LENGTH_SHORT).show();
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

    //region [CLICK EVENTS]
    private void createPostItem(int type) {
        if (type != 0) {
            adapter.createPostItem(type);
            listIsEmpty();

        }
    }
    //endregion

    //region [OPERATIONS]
    public void listIsEmpty() {
        if (adapter.getItemCount() == 0) {
            descriptionCreatePost.setVisibility(View.VISIBLE);
        } else
            descriptionCreatePost.setVisibility(View.GONE);
    }
    //endregion
}