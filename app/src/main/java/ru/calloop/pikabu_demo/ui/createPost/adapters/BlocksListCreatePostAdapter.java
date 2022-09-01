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
import java.util.stream.IntStream;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostItem> postItems;
    private boolean editModeIsActive;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListCreatePostAdapter() {
        postItems = new ArrayList<>(1);
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
                holder = new TextViewHolder(view);
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
                textViewHolder.getListener()
                        .updatePosition(textViewHolder.getAdapterPosition());
                textViewHolder.getTextView()
                        .setText(postItems.get(textViewHolder.getAdapterPosition()).getValue());
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

        return postItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return postItems == null ? 0 : postItems.size();
    }

    @Override
    public long getItemId(int position) {
        return postItems.get(position).getId();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private final EditText textView;
        private final CreatePostListener listener = new CreatePostListener();

        public TextViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.editText_textBlock_createPost);
            textView.addTextChangedListener(listener);
            View menu = view.findViewById(R.id.editing_menu);
            menu.setVisibility(editModeIsActive ? View.VISIBLE : View.GONE);
            Button deleteButton = menu.findViewById(R.id.button_delete_text_block_create_post2);
            deleteButton.setOnClickListener(v -> {
                removePostItem(getAdapterPosition());
            });
        }

        public EditText getTextView() {
            return textView;
        }

        public CreatePostListener getListener() {
            return listener;
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
        PostItem postItem = new PostItem(postItems.size(), type, null);
        postItems.add(postItem);
        notifyItemInserted(postItems.size() - 1);
    }

    public List<PostItem> getAdapterList() {
        return postItems;
    }

    public void updateAdapterList(List<PostItem> postItems) {
        this.postItems = postItems;
        notifyItemRangeChanged(0, this.postItems.size());
    }

    public void removePostItem(int position) {
        postItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, postItems.size());
    }

    public void editModeIsActive(boolean state) {
        editModeIsActive = state;

        if (!editModeIsActive) {
            IntStream.range(0, postItems.size()).forEach(i -> postItems.get(i).setPosition(i + 1));
        }
        notifyItemRangeChanged(0, postItems.size());
    }

    public class CreatePostListener implements TextWatcher {
        private int position;
        private Timer timer = new Timer();
        private final int DELAY = 1000;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            timer.cancel();
            timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (postItems.size() != 0) {
                        postItems.get(position).setValue(editable.toString());
                    }
                }
            }, DELAY);
        }
    }
}