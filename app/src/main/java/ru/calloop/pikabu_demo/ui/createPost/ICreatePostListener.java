package ru.calloop.pikabu_demo.ui.createPost;

import androidx.recyclerview.widget.RecyclerView;

public interface ICreatePostListener {
    void onTextViewUpdated(int position, String contentValue);

    void onClickRemoveItem(int position);

    void onClickAddItem(int type, int position);

    void requestDrag(RecyclerView.ViewHolder viewHolder);
}