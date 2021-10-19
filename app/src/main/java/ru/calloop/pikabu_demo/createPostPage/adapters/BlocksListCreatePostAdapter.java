package ru.calloop.pikabu_demo.createPostPage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostPage.Model.PostData;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private final List<PostData> postBlocks;
    private OnItemClickListener listener;

    private static final int TYPE_ADDBLOCK = 0;
    private static final int TYPE_TEXTBLOCK = 1;
    private static final int TYPE_IMAGEBLOCK = 2;

    public BlocksListCreatePostAdapter(List<PostData> postBlocks, OnItemClickListener listener) {
        this.postBlocks = postBlocks;
        this.listener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        if (holder.getItemViewType() == TYPE_ADDBLOCK) {
            AddBlockViewHolder addBlockViewHolder = (AddBlockViewHolder) holder;
        } else if (holder.getItemViewType() == TYPE_TEXTBLOCK) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
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

    public class AddBlockViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Button button;

        public AddBlockViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.button_add_block_create_post);

            button.setOnClickListener(view1 -> {
                Toast.makeText(view1.getContext(), "CLICKED", Toast.LENGTH_SHORT).show();
            });

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.editText_textBlock_createPost);
        }
    }

    public void createBlock(int type) {
        PostData emptyData = new PostData(0, 0, null);
        PostData postData = new PostData(type, 0, null);
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