package ru.calloop.pikabu_demo.ui.main.adapters;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostWithPostItems;

public class PostsDiffUtil extends DiffUtil.Callback {
    private final List<PostWithPostItems> oldList;
    private final List<PostWithPostItems> newList;

    public PostsDiffUtil(List<PostWithPostItems> oldList,
                         List<PostWithPostItems> newList) {
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
        PostWithPostItems oldItem = oldList.get(oldItemPosition);
        PostWithPostItems newItem = newList.get(newItemPosition);

        return oldItem.post.getId() == newItem.post.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PostWithPostItems oldProduct = oldList.get(oldItemPosition);
        PostWithPostItems newProduct = newList.get(newItemPosition);

        return oldProduct.postItemList.equals(newProduct.postItemList);
    }
}