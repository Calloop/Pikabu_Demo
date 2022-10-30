package ru.calloop.pikabu_demo.ui.createPost.adapters.holders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import ru.calloop.pikabu_demo.ui.createPost.ICreatePostListener;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull ViewBinding binding) {
        super(binding.getRoot());
    }

    public void bind(PostItem postItem, ICreatePostListener listener,
                     boolean actionModeState) {

    }

    public void bind(PostItem postItem) {

    }
}