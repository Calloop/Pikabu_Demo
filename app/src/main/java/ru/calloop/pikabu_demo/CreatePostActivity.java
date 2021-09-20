package ru.calloop.pikabu_demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import ru.calloop.pikabu_demo.adapter.ListItemCreatePost;
import ru.calloop.pikabu_demo.adapter.ListItemsAdapterCreatePost;
import ru.calloop.pikabu_demo.adapter.TypeA;

public class CreatePostActivity extends AppCompatActivity {

    Button buttonAddTextCreatePost, buttonAddPhotoCreatePost, buttonDeleteBlockCreatePost;
    TextView textViewDescriptionCreatePost;

    ArrayList<ListItemCreatePost> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

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
        ListItemsAdapterCreatePost adapterCreatePost = new ListItemsAdapterCreatePost(items);
        list.setAdapter(adapterCreatePost);


        //
        buttonAddTextCreatePost.setOnClickListener(v -> {
            TypeA typeA = new TypeA(items.size() + 1, 1);
            items.add(typeA);
            adapterCreatePost.notifyDataSetChanged();
            ListIsEmpty();
            Toast.makeText(CreatePostActivity.this, "TEXT" + adapterCreatePost.getItemCount(), Toast.LENGTH_SHORT).show();
        });

        buttonAddPhotoCreatePost.setOnClickListener(view -> {
            TypeA typeA = new TypeA(items.size() + 1, 2);
            items.add(typeA);
            adapterCreatePost.notifyDataSetChanged();
            ListIsEmpty();
            Toast.makeText(CreatePostActivity.this, "PHOTO" + adapterCreatePost.getItemCount(), Toast.LENGTH_SHORT).show();
        });

        //
        buttonDeleteBlockCreatePost.setOnClickListener(view -> {
            if(items.size() != 0) {
                items.remove(items.size());
                adapterCreatePost.notifyDataSetChanged();
                Toast.makeText(CreatePostActivity.this, "REMOVED" + adapterCreatePost.getItemCount(), Toast.LENGTH_SHORT).show();
            }

            ListIsEmpty();
            });

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

    private void ListIsEmpty() {
        if(items.size()== 0) {
            textViewDescriptionCreatePost.setVisibility(View.VISIBLE);
        }
        else
            textViewDescriptionCreatePost.setVisibility(View.GONE);
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