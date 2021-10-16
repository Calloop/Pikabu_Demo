package ru.calloop.pikabu_demo.createPostPage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostPage.models.PostData;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<PostData> postBlocks = new ArrayList<>();

    private static final int TYPE_ADDBLOCK = 0;
    private static final int TYPE_TEXTBLOCK = 1;
    private static final int TYPE_IMAGEBLOCK = 2;

    public BlocksListCreatePostAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADDBLOCK) {
            return new AddBlockViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_add_block_create_post, parent, false));

        } else if (viewType == TYPE_TEXTBLOCK) {
            return new TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_text_block_create_post, parent, false));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_image_block_create_post, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);

        if (type == TYPE_ADDBLOCK) {
        } else if (type == TYPE_TEXTBLOCK) {
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (postBlocks.get(position).getType() == 0) {
            return TYPE_ADDBLOCK;
        } else if (postBlocks.get(position).getType() == 1) {
            return TYPE_TEXTBLOCK;
        } else {
            return TYPE_IMAGEBLOCK;
        }
    }

    @Override
    public int getItemCount() {
        return postBlocks.size();
    }

    static class AddBlockViewHolder extends RecyclerView.ViewHolder {
        public AddBlockViewHolder(View view) {
            super(view);
        }
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.editText_textBlock_createPost);
        }
    }

    public void createBlock(int type) {
        PostData emptyData = new PostData(0, null, 0);
        PostData postData = new PostData(type, null, 0);
        if (postBlocks.size() == 0) {
            postBlocks.add(emptyData);
        }
        postBlocks.add(postData);
        postBlocks.add(emptyData);
        notifyDataSetChanged();
    }

    public boolean deleteBlock() {
        int counter = 0;

        if (postBlocks.size() > 0) {
            counter = 2;
            if (postBlocks.size() == 3) {
                counter = 3;
            }

            for (int i = 0; i < counter; i++) {
                postBlocks.remove(postBlocks.size() - 1);
            }
            notifyDataSetChanged();
            return true;
        } else return false;
    }
}