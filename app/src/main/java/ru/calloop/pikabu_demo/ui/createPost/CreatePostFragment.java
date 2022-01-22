package ru.calloop.pikabu_demo.ui.createPost;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostContract;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostPresenter;
import ru.calloop.pikabu_demo.createPostActivity.adapters.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.createPostActivity.fragments.MenuCreatePostFragment;
import ru.calloop.pikabu_demo.createPostActivity.models.Post;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;
import ru.calloop.pikabu_demo.signingActivity.models.SessionManager;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;

public class CreatePostFragment extends BaseFragment
        implements CreatePostContract.IView,
        BlocksListCreatePostAdapter.OnItemClickListener {

    private NavController navController;
    private AppCompatActivity activity;
    private ActionMode actionMode;
    private CreatePostPresenter presenter;
    private BlocksListCreatePostAdapter adapter;
    private TextView textViewDescriptionCreatePost;
    private RecyclerView recyclerView;

    @Override
    public BaseFragment providerFragment() {
        return new CreatePostFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        textViewDescriptionCreatePost = view.findViewById(R.id.textView_description_create_post);
        recyclerView = view.findViewById(R.id.list_create_post);
        activity = (AppCompatActivity) requireActivity();

        setNavController();
        setToolbar();
        setHomeViewModel();
        setCreatePostViewModel();
        setAdapter();

        return view;
    }

    //region [SET: TOOLBAR, FRAGMENT MANAGER, RECYCLER VIEW]
    private void setToolbar() {
        Toolbar toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);
    }

    private void setNavController() {
        NavHostFragment navHostFragment = (NavHostFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.activity_navigation_controller);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
    }

    private void setHomeViewModel() {
        HomeViewModel model = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        model.getState().observe(requireActivity(), type -> {
            Log.d("TEST", "" + type);
            if (type != 0) {
                PostItem postItem =
                        new PostItem(adapter.getItemCount() + 1, type, null);
                adapter.createPostItem(postItem);
                listIsEmpty();
            }
        });
    }

    private void setCreatePostViewModel() {
        CreatePostViewModel createPostViewModel = new ViewModelProvider(requireActivity())
                .get(CreatePostViewModel.class);
        createPostViewModel.getList().observe(requireActivity(), this::updateAdapter);
    }

    private void setAdapter() {
        presenter = new CreatePostPresenter();
        presenter.attachView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new BlocksListCreatePostAdapter( this);
        //adapter.setHasStableIds(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void updateAdapter(List<PostItem> postItemList) {
        adapter.setAdapterList(postItemList);
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
            if (actionMode == null) {
                actionMode = activity.startSupportActionMode(actionModeCallback);
            } else
                actionMode.finish();
        } else if (itemId == R.id.add_post_create_post) {
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            SharedPreferences sharedPreferences = activity
                    .getSharedPreferences(SessionManager.KEY, Context.MODE_PRIVATE);
            int accountId = sharedPreferences.getInt(SessionManager.ID, 0);
            presenter.insert(new Post(accountId), adapter.getAdapterList());
            navController.navigate(R.id.action_createPostFragment_to_homeFragment);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        int id = view.getId();

        if (id == R.id.button_delete_text_block_create_post2) {
            //adapter.addPreparedToDeleteItem(position);
            //adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            adapter.notifyItemChanged(position);
        }
    }
    //endregion

    //region [ACTION MODE]
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            //adapter.setDataModeIsActive(true);
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
            int id = item.getItemId();

            if (id == R.id.apply_edit_contextual_create_post) {
//                adapter.deletePostItems();
//                adapter.clearPreparedToDeleteItemList();
//                adapter.editModeIsActive(false);
//                adapter.notifyItemRangeChanged(0, adapter.getItemCount());
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //adapter.setDataModeIsActive(false);
            adapter.editModeIsActive(false);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
//            adapter.clearPreparedToDeleteItemList();
            //adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            actionMode = null;
        }
    };
    //endregion

    //region [CLICK EVENTS]
    private void buttonsCreatePost(int type) {

    }

//    private void buttonsCreatePost(int type) {
//        if (type != 0) {
//            someFragment.myClickMethod();
//            PostItem postItem = new PostItem(getItemCount() + 1, type, null);
//            adapter.createPostItem(type);
//            listIsEmpty();
//        }
//    }
    //endregion

    //region [OPERATIONS]
    private void listIsEmpty() {
        textViewDescriptionCreatePost
                .setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //endregion
}