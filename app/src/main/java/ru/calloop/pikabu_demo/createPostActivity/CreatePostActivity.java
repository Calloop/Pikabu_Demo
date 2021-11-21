package ru.calloop.pikabu_demo.createPostActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDbHelper;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.fragments.MenuCreatePostFragment;

public class CreatePostActivity extends AppCompatActivity implements CreatePostContract.IView {

    private CreatePostPresenter createPostPresenter;
    private BlocksListCreatePostAdapter adapter;
    private BlocksListCreatePostAdapter.OnItemClickListener listener;

    private TextView textViewDescriptionCreatePost;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        textViewDescriptionCreatePost = findViewById(R.id.textView_description_create_post);

        if (savedInstanceState == null) {
            setFragmentManager();
        }

        setToolbar();
        setAdapter();
        setOnClickListener();
    }

    //region [SET: TOOLBAR, FRAGMENT MANAGER, RECYCLER VIEW]
    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_create_post);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentManager() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container_content_buttons_create_post, MenuCreatePostFragment.class,
                        null)
                .commit();
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new BlocksListCreatePostAdapter(listener);

        RecyclerView recyclerView = findViewById(R.id.list_create_post);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        PostItemDbHelper postItemDbHelper = new PostItemDbHelper(this);
//        PostItemModel model = new PostItemModel(postItemDbHelper);
//        createPostPresenter = new CreatePostPresenter(model);
//        createPostPresenter.attachView(this);
//        createPostPresenter.loadPostItems();
    }

    private void setOnClickListener() {
        if (adapter != null) {
            adapter.setOnItemClickListener((view, position) -> {

            });

        }
    }
    //endregion

    //region [TOOLBAR OPTION MENU]
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_create_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.edit_create_post) {
            if (actionMode != null) {
                return false;
            }

            CreatePostActivity.this.startSupportActionMode(actionModeCallback);

            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (itemId == R.id.add_post_create_post) {
            createPostPresenter.addEntry(adapter.postItems);
            showToast("CREATED");
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
        adapter.loadPostItems(postItems);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region [CLICK EVENTS]
    public void buttonAddBlockCreatePost() {
        showToast("NEW BLOCK");
    }

    public void buttonAddTextCreatePostClicked() {
        //adapter.createPostItem(1);
        listIsEmpty();
        showToast("TEXT" + adapter.getItemCount());
    }

    public void buttonAddImageCreatePostClicked() {
        //adapter.createPostItem(2);
        listIsEmpty();
        showToast("IMAGE" + adapter.getItemCount());
    }

    public void buttonDeleteBlockCreatePostClicked() {
        if (adapter.deletePostItem()) {
            showToast("REMOVED");
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
    protected void onDestroy() {
        createPostPresenter.detachView();
        super.onDestroy();
    }
    //endregion
}