package ru.calloop.pikabu_demo.ui.createPost.adapters.holders;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.calloop.pikabu_demo.databinding.CreatePostTextPostItemBinding;
import ru.calloop.pikabu_demo.ui.createPost.ICreatePostListener;
import ru.calloop.pikabu_demo.ui.createPost.adapters.TextAdapterListener;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class TextViewHolder extends BaseViewHolder {

    private final TextAdapterListener textAdapterListener;
    private final EditText content;
    private final Button addContent;
    private final Button moveContent;
    private final Button deleteContent;
    private final ConstraintLayout editingMenu;
    private final ConstraintLayout addItemMenu;

    private final Button addTextContent;


    public TextViewHolder(@NonNull CreatePostTextPostItemBinding binding) {
        super(binding);
        content = binding.createPostTextTypeContent;
        addContent = binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost;
        moveContent = binding.includeCreatePostEditingMenu.buttonReplaceTextBlockCreatePost2;
        deleteContent = binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2;
        editingMenu = binding.includeCreatePostEditingMenu.editingMenuCreatePost;
        addItemMenu = binding.includeCreatePostAddPostItem.createPostContentButtons.getRoot();

        addTextContent = binding.includeCreatePostAddPostItem.createPostContentButtons
                .buttonAddTextCreatePost;

        textAdapterListener = new TextAdapterListener();
    }


    public void bind(@NonNull PostItem data, ICreatePostListener listener,
                     boolean actionModeState) {
        content.setText(data.getValue());
        content.setEnabled(!actionModeState);
        addContent.setVisibility(actionModeState ? View.GONE : View.VISIBLE);
        editingMenu.setVisibility(actionModeState ? View.VISIBLE : View.GONE);
        setListeners(listener, data.getType());
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setListeners(ICreatePostListener listener, int itemType) {
        textAdapterListener.setListener(listener);
        textAdapterListener.setPosition(getBindingAdapterPosition());
        content.addTextChangedListener(textAdapterListener);

        moveContent.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                listener.requestDrag(TextViewHolder.this);
                return true;
            }
            return false;
        });

        addContent.setOnClickListener(v -> {
            addContent.setVisibility(View.GONE);
            addItemMenu.setVisibility(View.VISIBLE);
        });

        addTextContent.setOnClickListener(v -> {
            listener.onClickAddItem(itemType, getLayoutPosition());
            addContent.setVisibility(View.VISIBLE);
            addItemMenu.setVisibility(View.GONE);
        });

        deleteContent.setOnClickListener(v ->
                listener.onClickRemoveItem(getBindingAdapterPosition()));
    }
}