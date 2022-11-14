package ru.calloop.pikabu_demo.adapters.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.adapters.main.holders.ImageViewHolder;
import ru.calloop.pikabu_demo.databinding.MainImagePostItemBinding;
import ru.calloop.pikabu_demo.databinding.MainTextPostItemBinding;
import ru.calloop.pikabu_demo.adapters.createPost.holders.BaseViewHolder;
import ru.calloop.pikabu_demo.adapters.main.holders.TextViewHolder;
import ru.calloop.pikabu_demo.data.models.PostItem;

public class PostItemsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<PostItem> postItems;

    public PostItemsAdapter() {
        postItems = new ArrayList<>(1);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            MainTextPostItemBinding binding =
                    MainTextPostItemBinding.inflate(inflater, parent, false);
            return new TextViewHolder(binding);
        } else if (viewType == 3) {
            MainImagePostItemBinding binding =
                    MainImagePostItemBinding.inflate(inflater, parent, false);
            return new ImageViewHolder(binding);
        }
        throw new IllegalArgumentException("The view type value of $viewType is not supported");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        viewHolder.bind(postItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return postItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public void updateList(List<PostItem> postItems) {
        this.postItems = postItems;
        notifyItemRangeChanged(0, this.postItems.size());
    }
}

