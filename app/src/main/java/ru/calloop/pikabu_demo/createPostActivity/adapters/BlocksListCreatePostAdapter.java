package ru.calloop.pikabu_demo.createPostActivity.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<RecyclerView.ViewHolder> viewHolderList = new ArrayList<>();

//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//    }

    public List<PostItem> postItemList = new ArrayList<>();
//    private OnItemClickListener listener;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    //public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;

        if (viewType == TYPE_TEXT_BLOCK) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_text_block_create_post, parent, false);
            holder = new TextViewHolder(view);
            viewHolderList.add(holder);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_image_block_create_post, parent, false);
            holder = new ImageViewHolder(view);
            viewHolderList.add(holder);
        }

        return holder;
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder.getItemViewType() == TYPE_TEXT_BLOCK) {
//            TextViewHolder textViewHolder = (TextViewHolder) holder;
//            textViewHolder.textView.setText();
//        } else {
//
//        }
        //holder.getTextView().setText(postItemList.get(position).getDataValue());
    }

    @Override
    public int getItemViewType(int position) {
        if (postItemList.get(position).getDataType() == 1) {
            return TYPE_TEXT_BLOCK;
        } else {
            return TYPE_IMAGE_BLOCK;
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

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public TextViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_createPost);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ImageViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView_imageBlock_createPost);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

//    public void loadPostItems(List<PostItem> postItemList) {
//        int posStart = postItemList.size();
//        this.postItemList.clear();
//        this.postItemList.addAll(postItemList);
//        notifyItemRangeInserted(posStart, postItemList.size());
//    }

    public void createPostItem(int type) {
        PostItem postItem = new PostItem(postItemList.size() + 1, type, null);
        //postItemList.add(postItemList.size(), postItem);
        postItemList.add(postItem);
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
        for (RecyclerView.ViewHolder holder :
                viewHolderList) {
            PostItem postItem = postItemList.get(holder.getAdapterPosition());

            if (postItem.getDataType() == TYPE_TEXT_BLOCK) {
                Log.d("GETDATATYPE", "" + postItem.getDataType());
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                postItem.setDataValue(textViewHolder.textView.getText().toString());
            } else {
                Log.d("GETDATATYPE", "" + postItem.getDataType());
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                postItem.setDataValue("ImageHolder");
            }
        }
    }
}