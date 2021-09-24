package ru.calloop.pikabu_demo.adapter;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.calloop.pikabu_demo.CreatePostActivity;
import ru.calloop.pikabu_demo.R;

public class ListItemsAdapterCreatePost extends
        RecyclerView.Adapter<ListItemsAdapterCreatePost.ItemHolder> {

    private final int TYPE_ITEM1 = 1;
    private final int TYPE_ITEM2 = 2;
    private final int TYPE_ITEM3 = 3;
    ArrayList<ListItemCreatePost> items;

    Button buttonAddBlockCreatePost;

    public ListItemsAdapterCreatePost(ArrayList<ListItemCreatePost> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_text_block_create_post, parent, false);

        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout
                .LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        FrameLayout frameLayout = view.findViewById(R.id.frameTest);

        if (viewType == TYPE_ITEM1) {
            EditText textBlockCreatePost = new EditText(view.getContext());
            textBlockCreatePost.setLayoutParams(frameLayoutParams);
            textBlockCreatePost.setEms(10);
            textBlockCreatePost.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            textBlockCreatePost.setHint("Введите текст");
            textBlockCreatePost.setId(R.id.text_block_create_post);

            frameLayout.addView(textBlockCreatePost);
        } else if (viewType == TYPE_ITEM2) {
            int imageResource = view.getResources().getIdentifier("@drawable/ic_launcher_background", null, view.getContext().getPackageName());

            ImageView imageBlockCreatePost = new ImageView(view.getContext());
            imageBlockCreatePost.setLayoutParams(frameLayoutParams);
            imageBlockCreatePost.setImageResource(imageResource);

            frameLayout.addView(imageBlockCreatePost);
        } else if (viewType == TYPE_ITEM3) {

        }


//        if (viewType == TYPE_ITEM1) {
//            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_text_block_create_post, parent, false);
//        } else if (viewType == TYPE_ITEM2) {
//            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_photo_block_create_post, parent, false);
//        } else if (viewType == TYPE_ITEM3) {
//            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_add_block_create_post, parent, false);
//
//            buttonAddBlockCreatePost = v.findViewById(R.id.button_add_block_create_post);
//
//            buttonAddBlockCreatePost.setOnClickListener(g -> {
//                System.out.println("linear click>>>>>>>>>>>>");
//            });
//        }
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        int type = getItemViewType(position);

        if (type == TYPE_ITEM1) {
            TypeA typeA = (TypeA) items.get(position);
        }
        if (type == TYPE_ITEM2) {
            TypeA typeA = (TypeA) items.get(position);
        }
        if (type == TYPE_ITEM3) {
            TypeA typeA = (TypeA) items.get(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = items.get(position).getItemType();
        if (type == 1) return TYPE_ITEM1;
        else if (type == 2) return TYPE_ITEM2;
        else return TYPE_ITEM3;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        ItemHolder(View itemView) {
            super(itemView);
        }
    }
}