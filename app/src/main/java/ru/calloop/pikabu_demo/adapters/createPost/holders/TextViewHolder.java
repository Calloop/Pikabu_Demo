package ru.calloop.pikabu_demo.adapters.createPost.holders;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import ru.calloop.pikabu_demo.adapters.createPost.listeners.ICreatePostListener;
import ru.calloop.pikabu_demo.adapters.createPost.listeners.TextAdapterListener;
import ru.calloop.pikabu_demo.databinding.CreatePostTextPostItemBinding;
import ru.calloop.pikabu_demo.data.models.PostItem;

public class TextViewHolder extends BaseViewHolder {

    private final ICreatePostListener createPostListener;
    private final TextAdapterListener textAdapterListener;
    private final EditText content;
    private final Button addContent;
    private final Button moveContent;
    private final Button deleteContent;
    private final ConstraintLayout editingMenu;
    private final ConstraintLayout addItemMenu;

    private final Button addTextContent;
    private final Button addCameraContent;
    private final Button addImageContent;
    private final Button addVideoContent;

    public TextViewHolder(@NonNull CreatePostTextPostItemBinding binding,
                          ICreatePostListener createPostListener) {
        super(binding);
        content = binding.createPostTextTypeContent;
        addContent = binding.includeCreatePostAddPostItem.buttonAddBlockCreatePost;
        moveContent = binding.includeCreatePostEditingMenu.buttonReplaceTextBlockCreatePost2;
        deleteContent = binding.includeCreatePostEditingMenu.buttonDeleteTextBlockCreatePost2;
        editingMenu = binding.includeCreatePostEditingMenu.editingMenuCreatePost;
        addItemMenu = binding.includeCreatePostAddPostItem.createPostContentButtons.getRoot();

        addTextContent = binding.includeCreatePostAddPostItem.createPostContentButtons
                .buttonAddTextCreatePost;
        addCameraContent = binding.includeCreatePostAddPostItem.createPostContentButtons
                .buttonUseCameraCreatePost;
        addImageContent = binding.includeCreatePostAddPostItem.createPostContentButtons
                .buttonAddImageCreatePost;
        addVideoContent = binding.includeCreatePostAddPostItem.createPostContentButtons
                .buttonAddVideoCreatePost;


        this.createPostListener = createPostListener;
        textAdapterListener = new TextAdapterListener();
    }


    public void bind(@NonNull PostItem data, boolean actionModeState) {
        setListeners(data.getType());
        content.setText(data.getValue());
        content.setEnabled(!actionModeState);
        addContent.setVisibility(actionModeState ? View.GONE : View.VISIBLE);
        editingMenu.setVisibility(actionModeState ? View.VISIBLE : View.GONE);

    }

    @SuppressLint("ClickableViewAccessibility")
    public void setListeners(int itemType) {
        textAdapterListener.setListener(createPostListener);
        textAdapterListener.setPosition(getAbsoluteAdapterPosition());
        content.addTextChangedListener(textAdapterListener);

        moveContent.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                createPostListener.requestDrag(TextViewHolder.this);
                return true;
            }
            return false;
        });

        addContent.setOnClickListener(v -> {
            addContent.setVisibility(View.GONE);
            addItemMenu.setVisibility(View.VISIBLE);
        });

        addTextContent.setOnClickListener(v -> {
            createPostListener.onClickAddItem(itemType, getAbsoluteAdapterPosition());
            addContent.setVisibility(View.VISIBLE);
            addItemMenu.setVisibility(View.GONE);
        });

        addCameraContent.setOnClickListener(v -> {
            Toast.makeText(content.getContext(), "Функция в разработке", Toast.LENGTH_SHORT).show();
            addContent.setVisibility(View.VISIBLE);
            addItemMenu.setVisibility(View.GONE);
        });

        addImageContent.setOnClickListener(v -> {
            Toast.makeText(content.getContext(), "Функция в разработке", Toast.LENGTH_SHORT).show();
            addContent.setVisibility(View.VISIBLE);
            addItemMenu.setVisibility(View.GONE);
        });

        addVideoContent.setOnClickListener(v -> {
            Toast.makeText(content.getContext(), "Функция в разработке", Toast.LENGTH_SHORT).show();
            addContent.setVisibility(View.VISIBLE);
            addItemMenu.setVisibility(View.GONE);
        });

        deleteContent.setOnClickListener(v ->
                createPostListener.onClickRemoveItem(getAbsoluteAdapterPosition()));
    }
}