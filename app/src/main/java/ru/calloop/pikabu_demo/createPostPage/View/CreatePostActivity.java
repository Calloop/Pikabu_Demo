package ru.calloop.pikabu_demo.createPostPage.View;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostPage.CreatePostContract;
import ru.calloop.pikabu_demo.createPostPage.Model.PostDataModel;
import ru.calloop.pikabu_demo.createPostPage.Model.PostData;
import ru.calloop.pikabu_demo.createPostPage.Presenter.CreatePostPresenter;
import ru.calloop.pikabu_demo.createPostPage.adapters.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.createPostPage.fragments.MenuCreatePostFragment;

public class CreatePostActivity extends AppCompatActivity {

    private CreatePostPresenter presenter;
    private BlocksListCreatePostAdapter adapter;
    private DbHelper dbHelper;
    private BlocksListCreatePostAdapter.OnItemClickListener listener;

    private List<PostData> list = new ArrayList<>();

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

        dbHelper = new DbHelper(this);
        PostDataModel model = new PostDataModel(dbHelper);
        presenter = new CreatePostPresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    private void setOnClickListener() {
        if (adapter != null)
        {
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

        if (itemId == R.id.add_post_create_post)
        {

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
    public void showData(List<PostData> postData) {
        //adapter.showData(postData);
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
        //presenter.buttonAddTextCreatePostClicked();
        adapter.createBlock(1);
        listIsEmpty();
        showToast("TEXT" + adapter.getItemCount());
    }

    public void buttonAddImageCreatePostClicked() {
        //presenter.buttonAddImageCreatePostClicked();
        adapter.createBlock(2);
        listIsEmpty();
        showToast("IMAGE" + adapter.getItemCount());
    }

    public void buttonDeleteBlockCreatePostClicked() {
        //presenter.buttonDeleteBlockCreatePostClicked();
        if (adapter.deleteBlock()) {
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

    @Override
    protected void onDestroy() {
        dbHelper.close();
        presenter.detachView();
        super.onDestroy();
    }
    //endregion
}