package ru.calloop.pikabu_demo.mainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostAndPostItem;

public class BlocksListMainAdapter extends RecyclerView.Adapter<BlocksListMainAdapter.ViewHolder> {

    public List<PostAndPostItem> postAndPostItemList;
    private Context context;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListMainAdapter(List<PostAndPostItem> postAndPostItemList) {
        this.postAndPostItemList = postAndPostItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT_BLOCK) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_text_block_main, parent, false));
        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_image_block_create_post, parent, false));
        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return postAndPostItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_main);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
