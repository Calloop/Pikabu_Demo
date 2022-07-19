package ru.calloop.pikabu_demo.ui.main.adapters;

import android.text.TextUtils;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostAndPostItem;

public class PostsDiffUtil extends DiffUtil.Callback {
    private final List<PostAndPostItem> oldList;
    private final List<PostAndPostItem> newList;

    public PostsDiffUtil(List<PostAndPostItem> oldList,
                         List<PostAndPostItem> newList) {
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
        PostAndPostItem oldItem = oldList.get(oldItemPosition);
        PostAndPostItem newItem = newList.get(newItemPosition);

        return oldItem.post.getId() == newItem.post.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PostAndPostItem newProduct = newList.get(newItemPosition);
        PostAndPostItem oldProduct = oldList.get(oldItemPosition);

        return newProduct.post.getId() == oldProduct.post.getId()
                && TextUtils.equals(newProduct.post.getHeadline(), oldProduct.post.getHeadline())
                && newProduct.post.getUserId() == oldProduct.post.getUserId();
    }
}