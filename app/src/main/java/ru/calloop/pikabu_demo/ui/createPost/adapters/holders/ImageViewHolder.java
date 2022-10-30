package ru.calloop.pikabu_demo.ui.createPost.adapters.holders;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.CreatePostImagePostItemBinding;
import ru.calloop.pikabu_demo.ui.createPost.ICreatePostListener;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class ImageViewHolder extends BaseViewHolder {

    private final ImageView content;
    private final Button addContent;
    private final Button moveContent;
    private final Button deleteContent;
    private final ConstraintLayout editingMenu;
    private final ConstraintLayout addItemMenu;
    private final Button addImageContent;

    public ImageViewHolder(@NonNull CreatePostImagePostItemBinding binding) {
        super(binding);
        content = binding.createPostImageTypeContent;
        addContent = binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost;
        moveContent = binding.includeCreatePostEditingMenu.buttonReplaceTextBlockCreatePost2;
        deleteContent = binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2;
        editingMenu = binding.includeCreatePostEditingMenu.editingMenuCreatePost;
        addItemMenu = binding.includeCreatePostAddPostItem.createPostContentButtons.getRoot();

        addImageContent = binding.includeCreatePostAddPostItem.createPostContentButtons.
                buttonAddImageCreatePost;
    }

    public void bind(@NonNull PostItem data, ICreatePostListener listener,
                     boolean actionModeState) {
        if (actionModeState) {
            Picasso.get()
                    .load("file://" + data.getValue())
                    .placeholder(R.drawable.image_placeholder)
                    .resize(0, 300)
                    .into(content);
        } else {
            Picasso.get()
                    .load("file://" + data.getValue())
                    .placeholder(R.drawable.image_placeholder)
                    .fit()
                    .centerInside()
                    .into(content);
        }

        Log.d("TAG", "bind: " + data.getValue());

        addContent.setVisibility(actionModeState ? View.GONE : View.VISIBLE);
        editingMenu.setVisibility(actionModeState ? View.VISIBLE : View.GONE);
        setListeners(listener, data.getType());
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setListeners(ICreatePostListener listener, int itemType) {
        moveContent.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                listener.requestDrag(ImageViewHolder.this);
                return true;
            }
            return false;
        });

        addContent.setOnClickListener(v -> {
            addContent.setVisibility(View.GONE);
            addItemMenu.setVisibility(View.VISIBLE);
        });

        addImageContent.setOnClickListener(v -> {
            listener.onClickAddItem(itemType, getLayoutPosition());
            addContent.setVisibility(View.VISIBLE);
            addItemMenu.setVisibility(View.GONE);
        });

        deleteContent.setOnClickListener(v ->
                listener.onClickRemoveItem(getBindingAdapterPosition()));
    }
}