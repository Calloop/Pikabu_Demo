package ru.calloop.pikabu_demo.mainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public class BlocksListMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<PostItem> postItems;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListMainAdapter(List<PostItem> postItems) {
        this.postItems = postItems;
    }

//    public void setData(List<PostItem> postItems) {
//        this.postItems = postItems;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT_BLOCK) {
            return new BlocksListMainAdapter.TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_text_block_main, parent, false));
        } else {
            return new BlocksListMainAdapter.TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_image_block_create_post, parent, false));
        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_TEXT_BLOCK) {
            BlocksListMainAdapter.TextViewHolder textViewHolder = (BlocksListMainAdapter.TextViewHolder) holder;

            textViewHolder.getTextView().setText(postItems.get(position).getDataValue());
        }
        // тут создавать холдер и с ним делать действия типа нажатий
    }

    @Override
    public int getItemViewType(int position) {
        if (postItems.get(position).getDataType() == 1) {
            return TYPE_TEXT_BLOCK;
        } else if (postItems.get(position).getDataType() == 2) {
            return TYPE_IMAGE_BLOCK;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_main);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
