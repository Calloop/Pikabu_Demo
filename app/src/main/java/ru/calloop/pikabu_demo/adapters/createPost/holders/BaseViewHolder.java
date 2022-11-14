package ru.calloop.pikabu_demo.adapters.createPost.holders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import ru.calloop.pikabu_demo.data.models.PostItem;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull ViewBinding binding) {
        super(binding.getRoot());
    }

    public void bind(PostItem postItem, boolean actionModeState) {

    }

    public void bind(PostItem postItem) {

    }
}