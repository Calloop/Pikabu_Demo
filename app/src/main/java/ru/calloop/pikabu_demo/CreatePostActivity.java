package ru.calloop.pikabu_demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import ru.calloop.pikabu_demo.adapter.ListCreatePostModel;
import ru.calloop.pikabu_demo.adapter.ListItemCreatePost;
import ru.calloop.pikabu_demo.adapter.ListItemsAdapterCreatePost;
import ru.calloop.pikabu_demo.adapter.TypeA;

public class CreatePostActivity extends AppCompatActivity {

    Button buttonAddTextCreatePost, buttonAddPhotoCreatePost, buttonDeleteBlockCreatePost,
            buttonAddBlockCreatePost;
    TextView textViewDescriptionCreatePost;

    ArrayList<ListItemCreatePost> listItemCreatePost = new ArrayList<>();
    ListItemsAdapterCreatePost adapterCreatePost;

    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        buttonAddBlockCreatePost = findViewById(R.id.button_add_block_create_post);
        buttonAddTextCreatePost = findViewById(R.id.button_add_text_create_post);
        buttonAddPhotoCreatePost = findViewById(R.id.button_add_photo_create_post);
        buttonDeleteBlockCreatePost = findViewById(R.id.button_delete_block_create_post);
        textViewDescriptionCreatePost = findViewById(R.id.textView_description_create_post);

        Toolbar toolbar = findViewById(R.id.toolbar_create_post);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //
        RecyclerView list = findViewById(R.id.list_create_post);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapterCreatePost = new ListItemsAdapterCreatePost(listItemCreatePost);
        list.setAdapter(adapterCreatePost);

        // Begin the transaction
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_container_view_create_post, CreatePostAddBlockFragment.class, getAttributionTag())
//                .add(R.id.fragment_container_view_create_post, CreatePostAddBlockFragment.class, null)
//                .add(R.id.fragment_container_view_create_post, CreatePostAddBlockFragment.class, null)
//                .commit();


        //
//        FragmentManager fragmentManager = getChildFragmentManager();
//
//        for (int x = 1; x < 5; x = x + 1) {
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.add(R.id.fragment_contentrow, new MyChildFragment(), "Tag " + x);
//            ft.addToBackStack(null);
//            ft.commit();
//        }

    }

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

            Toast.makeText(this,"test", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //

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
                Toast.makeText(CreatePostActivity.this, "EDITING APPLIED", Toast.LENGTH_SHORT).show();
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

    //

    private void ListIsEmpty() {
        if (listItemCreatePost.size() == 0) {
            textViewDescriptionCreatePost.setVisibility(View.VISIBLE);
        } else
            textViewDescriptionCreatePost.setVisibility(View.GONE);
    }

    public void ButtonAddBlockCreatePost() {
        Toast.makeText(CreatePostActivity.this, "TODO PANEL", Toast.LENGTH_SHORT).show();
    }

    public void ButtonAddTextCreatePostClicked() {
        TypeA typeB = new TypeA(listItemCreatePost.size() + 1, 3);
        TypeA typeA = new TypeA(listItemCreatePost.size() + 1, 1);

        listItemCreatePost.add(typeB);
        listItemCreatePost.add(typeA);

        adapterCreatePost.notifyDataSetChanged();
        ListIsEmpty();
        Toast.makeText(CreatePostActivity.this, "TEXT" + adapterCreatePost.getItemCount(), Toast.LENGTH_SHORT).show();
    }

    public void ButtonAddPhotoCreatePostClicked() {
        TypeA typeB = new TypeA(listItemCreatePost.size() + 1, 3);
        TypeA typeA = new TypeA(listItemCreatePost.size() + 1, 2);

        listItemCreatePost.add(typeB);
        listItemCreatePost.add(typeA);

        adapterCreatePost.notifyDataSetChanged();
        ListIsEmpty();
        Toast.makeText(CreatePostActivity.this, "PHOTO" + adapterCreatePost.getItemCount(), Toast.LENGTH_SHORT).show();

    }

    public void ButtonDeleteBlockCreatePostClicked() {
        if (listItemCreatePost.size() > 0) {
            for (int i = 0; i < 2; i++) {
                listItemCreatePost.remove(listItemCreatePost.size() - 1);
            }

            adapterCreatePost.notifyDataSetChanged();
            Toast.makeText(CreatePostActivity.this, "REMOVED" + (adapterCreatePost.getItemCount() + 1), Toast.LENGTH_SHORT).show();
        }

        ListIsEmpty();
    }

//    private void loadFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_container_view_create_post, fragment)
//                .commit();
//    }

//    public void TestButton(View view) {
//        Log.e("button", "pressed");
//        Toast.makeText(CreatePostActivity.this, "accountModel.toString()", Toast.LENGTH_SHORT).show();
//    }
}