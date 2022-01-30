package ru.calloop.pikabu_demo.ui.createPost.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private List<PostItem> postItemList;
    private List<PostItem> dbPostItemList;

    private final List<Integer> prepareToDeleteItemsList = new ArrayList<>(10);
    private final List<LayoutParams> layoutParamsList =
            new ArrayList<>(10);
    private LayoutParams editModeLayoutParams;
    private OnItemClickListener listener;
    private boolean editModeIsActive, setDataModeIsActive;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListCreatePostAdapter() {
        postItemList = new ArrayList<>(1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case TYPE_TEXT_BLOCK:
                view = layoutInflater
                        .inflate(R.layout
                                .fragment_text_block_create_post, parent, false);
                holder = new TextViewHolder(view, listener, new CreatePostListener());
                break;
            case TYPE_IMAGE_BLOCK:
                view = layoutInflater
                        .inflate(R.layout
                                .fragment_image_block_create_post, parent, false);
                holder = new ImageViewHolder(view);
                break;
        }

        return Objects.requireNonNull(holder);
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_TEXT_BLOCK:
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                //textViewHolder.deleteTextBlock.setOnClickListener(textViewHolder);
                textViewHolder.createPostListener.updatePosition(textViewHolder.getAdapterPosition());
                textViewHolder.textView.setText(postItemList.get(textViewHolder.getAdapterPosition()).getDataValue());
//            textViewHolder.textView.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//                    postItemList.get(textViewHolder.getAdapterPosition()).setDataValue(editable.toString());
//                }
//            });

//            if (setDataModeIsActive) {
//                for (PostItem postItem :
//                        postItemList) {
//                    if (position == postItem.getDataPosition() - 1) {
//                        postItem.setDataValue(textViewHolder.getTextViewValue());
//                        Log.d("LISTING", "holder: " + position + "; pos :" + postItem.getDataPosition() + "; value: " + postItem.getDataValue());
//
//                    }
//                }
//            } else {
//                for (PostItem postItem :
//                        postItemList) {
//                    if (position == postItem.getDataPosition() - 1) {
//                        textViewHolder.setTextViewValue(postItem.getDataValue());
//                        Log.d("LISTING", "holder: " + position + "; pos :" + postItem.getDataPosition() + "; value: " + postItem.getDataValue());
//
//                    }
//                }
//            }


//            Log.d("VALUE", "BEFORE " + textViewHolder.getAdapterPosition() + ": "  + postItemList.get(position).getDataValue());
//            postItemList.get(textViewHolder.getAdapterPosition()).setDataValue(textViewHolder.getTextViewValue());
//            Log.d("VALUE", "AFTER : " + textViewHolder.getAdapterPosition() + ": "  + postItemList.get(position).getDataValue());
//            if (editModeIsActive) {
//                textViewHolder.menu.setVisibility(View.VISIBLE);
//
////                for (int itemPreparedToDelete :
////                        prepareToDeleteItemsList) {
////                    if (itemPreparedToDelete == textViewHolder.getAdapterPosition()) {
////                        textViewHolder.itemView.setVisibility(View.GONE);
////                    }
////                }
//            } else {
//                textViewHolder.menu.setVisibility(View.GONE);
//
////                for (int itemPreparedToDelete :
////                        prepareToDeleteItemsList) {
////                    if (itemPreparedToDelete == textViewHolder.getAdapterPosition()) {
////                        textViewHolder.itemView.setVisibility(View.VISIBLE);
////                    }
////                }
//            }

                if (editModeIsActive) {
                    textViewHolder.menu.setVisibility(View.VISIBLE);

//                textViewHolder.deleteTextBlock.setOnClickListener(view -> {
//                    Objects.requireNonNull(textViewHolder.itemView).setVisibility(View.GONE);
//                    Objects.requireNonNull(textViewHolder.itemView)
//                            .setLayoutParams(new ViewGroup.LayoutParams(0, 0));
//                });

                } else {
                    textViewHolder.menu.setVisibility(View.GONE);

//                if (textViewHolder.itemView.getVisibility() == View.GONE) {
//                    Objects.requireNonNull(textViewHolder.itemView).setVisibility(View.VISIBLE);
//                    Objects.requireNonNull(textViewHolder.itemView)
//                            .setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//                }
                }
//        } else {
//
                break;
            case TYPE_IMAGE_BLOCK:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if (postItemList.get(position).getDataType() == 1) {
//            return TYPE_TEXT_BLOCK;
//        } else {
//            return TYPE_IMAGE_BLOCK;
//        }
//
        return postItemList.get(position).getDataType();
    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final EditText textView;
        private final Button deleteTextBlock;
        private final View menu;
        private final OnItemClickListener listener;
        private final CreatePostListener createPostListener;

        public TextViewHolder(View view, OnItemClickListener listener, CreatePostListener createPostListener) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_createPost);
            deleteTextBlock = view.findViewById(R.id.button_delete_text_block_create_post2);
            menu = view.findViewById(R.id.editing_menu);

            this.listener = listener;
            this.createPostListener = createPostListener;
            view.setOnClickListener(this);
            textView.addTextChangedListener(createPostListener);
        }

        public String getTextViewValue() {
            return textView.getText().toString();
        }

        public void setTextViewValue(String value) {
            textView.setText(value);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == R.id.button_delete_text_block_create_post2) {
                this.listener.onItemClick(view, getAdapterPosition());

            }
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

//    private TextViewHolder editManager(View view, ViewGroup.LayoutParams layoutParams) {
//        for (int itemPreparedToDelete :
//                prepareToDeleteItemsList) {
//            if (itemPreparedToDelete == textViewHolder.getAdapterPosition()) {
//                textViewHolder.itemView.setVisibility(view);
//                layoutParamsList.add(textViewHolder.itemView.getLayoutParams());
//                textViewHolder.itemView
//                        .setLayoutParams(layoutParamsList.get(itemPreparedToDelete));
//            }
//        }
//        return
//    }

    public void createPostItem(PostItem postItem) {
        postItemList.add(postItem);
        notifyItemInserted(getItemCount());
    }

    public void deletePostItems() {
        if (getItemCount() > 0) {
            for (int itemPreparedToDelete :
                    prepareToDeleteItemsList) {
                postItemList.remove(itemPreparedToDelete);
                notifyItemRemoved(itemPreparedToDelete);
                notifyItemRangeChanged(itemPreparedToDelete, getItemCount());
            }
        }
    }

    public List<PostItem> getAdapterList() {
        return postItemList;
    }

    public void setAdapterList(List<PostItem> postItemList) {
        this.postItemList = postItemList;
        notifyItemRangeInserted(0, postItemList.size());
    }

    public void saveDbList() {

    }

    public void addPreparedToDeleteItem(int position) {
        prepareToDeleteItemsList.add(position);
    }

    public void clearPreparedToDeleteItemList() {
        prepareToDeleteItemsList.clear();
        layoutParamsList.clear();
    }

    public void editModeIsActive(boolean state) {
        editModeIsActive = state;
    }

    public void setDataModeIsActive(boolean state) {
        setDataModeIsActive = state;
    }

    public class CreatePostListener implements TextWatcher {
        private int position;
        private Timer timer;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (timer != null) {
                timer.cancel();
            }

            postItemList.get(position).setDataValue(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // do your actual work here
                }
            }, 200);
        }
    }
}