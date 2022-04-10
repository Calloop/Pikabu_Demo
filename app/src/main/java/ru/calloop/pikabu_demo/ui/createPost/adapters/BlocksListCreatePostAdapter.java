package ru.calloop.pikabu_demo.ui.createPost.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private List<PostItem> localPostItemList;
    private final List<PostItem> prepareToDeleteItemsList;
    private boolean editModeIsActive;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListCreatePostAdapter() {
        localPostItemList = new ArrayList<>(1);
        prepareToDeleteItemsList = new ArrayList<>(1);
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
                holder = new TextViewHolder(view, new CreatePostListener());
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

                textViewHolder.createPostListener.updatePosition(textViewHolder.getAdapterPosition());
                textViewHolder.textView.setText(localPostItemList.get(textViewHolder.getAdapterPosition()).getDataValue());
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

                //ViewGroup.LayoutParams layoutParams = textViewHolder.itemView.getLayoutParams();

                if (editModeIsActive) {
                    textViewHolder.menu.setVisibility(View.VISIBLE);

                    textViewHolder.deleteButton.setOnClickListener(view -> {
                        addPreparedToDeleteItem(textViewHolder.getAdapterPosition());
//                        Objects.requireNonNull(textViewHolder.itemView).setVisibility(View.GONE);
//
//                        layoutParams.width = 0;
//                        layoutParams.height = 0;
//                        Objects.requireNonNull(textViewHolder.itemView)
//                                .setLayoutParams(layoutParams);
                    });
                } else {
                    textViewHolder.menu.setVisibility(View.GONE);
//
//                    if (textViewHolder.itemView.getVisibility() == View.GONE) {
//                        Objects.requireNonNull(textViewHolder.itemView).setVisibility(View.VISIBLE);
//
//                        layoutParams.width = 0;
//                        layoutParams.height = 0;
//                        Objects.requireNonNull(textViewHolder.itemView)
//                                .setLayoutParams(layoutParams);
//                    }
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
        return localPostItemList.get(position).getDataType();
    }

    @Override
    public int getItemCount() {
        return localPostItemList == null ? 0 : localPostItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private final EditText textView;
        private final View menu;
        private final Button deleteButton;
        private final CreatePostListener createPostListener;

        public TextViewHolder(View view, CreatePostListener createPostListener) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_createPost);
            menu = view.findViewById(R.id.editing_menu);
            this.createPostListener = createPostListener;
            deleteButton = menu.findViewById(R.id.button_delete_text_block_create_post2);
            textView.addTextChangedListener(createPostListener);
        }

        public String getTextViewValue() {
            return textView.getText().toString();
        }

        public void setTextViewValue(String value) {
            textView.setText(value);
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

    public void createPostItem(int type) {
        PostItem postItem = new PostItem(localPostItemList.size(), type, null);
        localPostItemList.add(postItem);
        notifyItemInserted(localPostItemList.size() - 1);
    }

    public List<PostItem> getAdapterList() {
        return localPostItemList;
    }

    public void updateList(List<PostItem> postItems) {
        localPostItemList.clear();
        localPostItemList = postItems;
        notifyItemRangeChanged(0, localPostItemList.size());
    }

    public List<PostItem> getPrepareToDeleteItemsList() {
        return prepareToDeleteItemsList;
    }

    public void addPreparedToDeleteItem(int position) {
        prepareToDeleteItemsList.add(localPostItemList.get(position));
        localPostItemList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearPreparedToDeleteItemList() {
        prepareToDeleteItemsList.clear();
    }

    public void editModeIsActive(boolean state) {
        editModeIsActive = state;
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
            localPostItemList.get(position).setDataValue(charSequence.toString());
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