package ru.calloop.pikabu_demo.ui.models.diffUtils;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostItem;

public class PostItemsDiffUtil extends DiffUtil.Callback {
    private final List<PostItem> oldList;
    private final List<PostItem> newList;

    public PostItemsDiffUtil(List<PostItem> oldList, List<PostItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        PostItem oldItem = oldList.get(oldItemPosition);
        PostItem newItem = newList.get(newItemPosition);

        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PostItem oldItem = oldList.get(oldItemPosition);
        PostItem newItem = newList.get(newItemPosition);

        boolean itemValue = true;

        if (oldItem.getValue() != null) {
            itemValue = oldItem.getValue().equals(newItem.getValue());
        } else {
            if (newItem.getValue() != null) {
                itemValue = false;
            }
        }

        return oldItem.getPosition() == newItem.getPosition()
                && itemValue
                && oldItem.getType() == newItem.getType();
    }
}