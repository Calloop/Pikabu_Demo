package ru.calloop.pikabu_demo.ui.createPost.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.CreatePostImageTypePostBinding;
import ru.calloop.pikabu_demo.databinding.CreatePostTextTypePostBinding;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<PostItem> postItems;
    private boolean editModeIsActive;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListCreatePostAdapter() {
        postItems = new ArrayList<>(1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_TEXT_BLOCK:
                CreatePostTextTypePostBinding textBinding =
                        CreatePostTextTypePostBinding
                                .inflate(inflater, parent, false);
                return new TextViewHolder(textBinding);
            case TYPE_IMAGE_BLOCK:
                CreatePostImageTypePostBinding imageBinding =
                        CreatePostImageTypePostBinding
                                .inflate(inflater, parent, false);
                return new ImageViewHolder(imageBinding);
            default:
                throw new RuntimeException();
        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();

        switch (holder.getItemViewType()) {
            case TYPE_TEXT_BLOCK:
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                textViewHolder.binding.createPostTextTypeContent
                        .setText(postItems.get(adapterPosition).getValue());

                textViewHolder.binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost
                        .setVisibility(editModeIsActive ? View.GONE : View.VISIBLE);
                textViewHolder.binding.includeCreatePostEditingMenu.editingMenuCreatePost
                        .setVisibility(editModeIsActive ? View.VISIBLE : View.GONE);
                break;
            case TYPE_IMAGE_BLOCK:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.binding.createPostImageTypeContent
                        .setImageResource(R.drawable.ic_launcher_background);

                imageViewHolder.binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost
                        .setVisibility(editModeIsActive ? View.GONE : View.VISIBLE);
                imageViewHolder.binding.includeCreatePostEditingMenu.editingMenuCreatePost
                        .setVisibility(editModeIsActive ? View.VISIBLE : View.GONE);
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
        return postItems.size();
    }

    @Override
    public long getItemId(int position) {
        return postItems.get(position).getId();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private final CreatePostTextTypePostBinding binding;
        private final EditText createPostTextTypeContent;
        private final CreatePostListener listener = new CreatePostListener();

        public TextViewHolder(@NonNull CreatePostTextTypePostBinding textBinding) {
            super(textBinding.getRoot());
            binding = textBinding;
            createPostTextTypeContent = textBinding.createPostTextTypeContent;
            setListeners();
        }

        private void setListeners() {
            binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2
                    .setOnClickListener(v -> removePostItem(getAdapterPosition()));

            createPostTextTypeContent.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    createPostTextTypeContent.addTextChangedListener(listener);
                    listener.updatePosition(getAdapterPosition());
                } else {
                    createPostTextTypeContent.removeTextChangedListener(listener);
                }
            });
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private final CreatePostImageTypePostBinding binding;
        private ImageView createPostImageTypeContent;

        public ImageViewHolder(@NonNull CreatePostImageTypePostBinding imageBinding) {
            super(imageBinding.getRoot());
            binding = imageBinding;
            createPostImageTypeContent = binding.createPostImageTypeContent;
            setListeners();
        }

        private void setListeners() {
            binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2
                    .setOnClickListener(v -> removePostItem(getAdapterPosition()));
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
        this.postItems.clear();
        this.postItems.addAll(postItems);
        notifyItemRangeInserted(0, postItems.size());
    }

    public void removePostItem(int position) {
        postItems.remove(position);
        notifyItemRemoved(position);
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
        }

        @Override
        public void afterTextChanged(Editable editable) {
            timer = new Timer();
            int DELAY = 250;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    postItems.get(position).setValue(editable.toString());
                }
            }, DELAY);
        }
    }
}