package ru.calloop.pikabu_demo.ui.createPost;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostContract;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostPresenter;
import ru.calloop.pikabu_demo.createPostActivity.adapters.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;

public class CreatePostFragment extends BaseFragment implements CreatePostContract.IView {

    private AppCompatActivity activity;
    private Toolbar toolbar;

    private CreatePostPresenter presenter;
    private BlocksListCreatePostAdapter adapter;
    private BlocksListCreatePostAdapter.OnItemClickListener listener;

    private TextView textViewDescriptionCreatePost;
    private ActionMode actionMode;

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
    public View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        HomeViewModel model = new ViewModelProvider(this.requireActivity()).get(HomeViewModel.class);
        model.getState().observe(this, this::buttonsCreatePost);

        activity = (AppCompatActivity) getActivity();


        textViewDescriptionCreatePost = view.findViewById(R.id.textView_description_create_post);

        setToolbar();
        setAdapter(view);
        //setOnClickListener();

        return view;
    }

    //region [SET: TOOLBAR, FRAGMENT MANAGER, RECYCLER VIEW]
    private void setToolbar() {
        assert activity != null;
        toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);
    }

//    private void setFragmentManager() {
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.container_content_buttons_create_post, MenuCreatePostFragment.class,
//                        null)
//                .commit();
//    }

    private void setAdapter(View view) {
        presenter = new CreatePostPresenter();
        presenter.attachView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new BlocksListCreatePostAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.list_create_post);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        if (adapter != null) {
//            adapter.setOnItemClickListener((view, position) -> {
//
//            });

        }
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
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.edit_create_post) {
//            if (actionMode != null) {
//                return false;
//            }
//
//            CreatePostFragment.this.startSupportActionMode(actionModeCallback);
//
//            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        if (itemId == R.id.add_post_create_post) {
//            adapter.savePostItems();
//            presenter.insert(new Post(0), adapter.postItemList);
//            startActivity(new Intent(CreatePostFragment.this, MainActivity.class));
//        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region [ACTION MODE]
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.apply_edit_contextual_create_post) {
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
    private void buttonsCreatePost(int type) {
        Log.d("TEST", "" + type);
        if (type != 0) {
            adapter.createPostItem(type);
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
    public void onDestroy() {
        //presenter.detachView();
        super.onDestroy();
    }
    //endregion
}