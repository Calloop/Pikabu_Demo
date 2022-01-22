package ru.calloop.pikabu_demo.ui.createPost;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;

public class CreatePostDiffUtilCallback extends DiffUtil.Callback {
    private final List<PostItem> oldList;
    private final List<PostItem> newList;

    public CreatePostDiffUtilCallback(List<PostItem> oldList, List<PostItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        PostItem oldItem = oldList.get(oldItemPosition);
        PostItem newItem = newList.get(newItemPosition);
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PostItem oldProduct = oldList.get(oldItemPosition);
        PostItem newProduct = newList.get(newItemPosition);
        return oldProduct.equals(newProduct)
                && oldProduct.getDataPosition() == newProduct.getDataPosition();
    }
}
