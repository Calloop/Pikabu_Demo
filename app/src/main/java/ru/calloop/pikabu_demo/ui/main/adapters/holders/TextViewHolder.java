package ru.calloop.pikabu_demo.ui.main.adapters.holders;

import android.widget.TextView;

import androidx.annotation.NonNull;

import ru.calloop.pikabu_demo.databinding.MainTextPostItemBinding;
import ru.calloop.pikabu_demo.ui.createPost.adapters.holders.BaseViewHolder;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class TextViewHolder extends BaseViewHolder {

    private final TextView content;

    public TextViewHolder(@NonNull MainTextPostItemBinding binding) {
        super(binding);
        content = binding.mainTextTypeContent;
    }

    @Override
    public void bind(PostItem postItem) {
        super.bind(postItem);
        content.setText(postItem.getValue());
    }
}
