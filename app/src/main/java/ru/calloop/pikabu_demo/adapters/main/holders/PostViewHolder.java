package ru.calloop.pikabu_demo.adapters.main.holders;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.adapters.main.PostItemsAdapter;
import ru.calloop.pikabu_demo.databinding.MainPostBinding;
import ru.calloop.pikabu_demo.data.models.PostWithPostItems;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private final MainPostBinding binding;
    private final TextView postHeadline;
    private PostItemsAdapter adapter;

    public PostViewHolder(@NonNull MainPostBinding binding, RecyclerView recyclerView) {
        super(binding.getRoot());
        this.binding = binding;
        postHeadline = binding.mainPostHeadline;
        setPostItemsRecycler(recyclerView);
    }

    public void bind(@NonNull PostWithPostItems postWithPostItems) {
        adapter.updateList(postWithPostItems.postItemList);
        postHeadline.setText(postWithPostItems.post.getHeadline());
    }

    private void setPostItemsRecycler(@NonNull RecyclerView recyclerView) {
        adapter = new PostItemsAdapter();
        recyclerView.setAdapter(adapter);
    }
}
