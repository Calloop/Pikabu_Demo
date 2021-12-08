package ru.calloop.pikabu_demo.createPostActivity;

import android.util.Log;
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
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<BlocksListCreatePostAdapter.ViewHolder> {
    private final List<ViewHolder> viewHolderList = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public List<PostItem> postItemList = new ArrayList<>();
    private OnItemClickListener listener;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    //public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT_BLOCK) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_text_block_create_post, parent, false));
            viewHolderList.add(holder);
            return holder;
        } else {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_image_block_create_post, parent, false));
            viewHolderList.add(holder);
            return holder;
        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(postItemList.get(position).getDataValue());
    }

    @Override
    public int getItemViewType(int position) {
        if (postItemList.get(position).getDataType() == 1) {
            return TYPE_TEXT_BLOCK;
        } else if (postItemList.get(position).getDataType() == 2) {
            return TYPE_IMAGE_BLOCK;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

//    public class AddBlockViewHolder extends RecyclerView.ViewHolder
//            implements View.OnClickListener {
//        private Button button;
//
//        public AddBlockViewHolder(View view) {
//            super(view);
//            button = view.findViewById(R.id.button_add_block_create_post);
//
//            button.setOnClickListener(view1 -> {
//                Toast.makeText(view1.getContext(), "CLICKED", Toast.LENGTH_SHORT).show();
//            });
//
//            view.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//        }
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_createPost);
        }

        public TextView getTextView() {
            return textView;
        }
    }

//    public void loadPostItems(List<PostItem> postItemList) {
//        int posStart = postItemList.size();
//        this.postItemList.clear();
//        this.postItemList.addAll(postItemList);
//        notifyItemRangeInserted(posStart, postItemList.size());
//    }

    public void createPostItem(int type) {
        PostItem postItem = new PostItem(postItemList.size(), type, null);
        postItemList.add(postItemList.size(), postItem);
        notifyItemInserted(postItemList.size());
    }

    public boolean deletePostItem() {
        if (getItemCount() > 0) {
            postItemList.remove(getItemCount() - 1);
            notifyItemRemoved(getItemCount());
            return true;
        } else return false;
    }

    public void savePostItems() {
        for (ViewHolder holder :
                viewHolderList) {
            postItemList.get(holder.getAdapterPosition()).setDataValue(holder.textView.getText().toString());
        }
    }
}