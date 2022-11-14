package ru.calloop.pikabu_demo.adapters.createPost;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.calloop.pikabu_demo.databinding.CreatePostTextPostItemBinding;
import ru.calloop.pikabu_demo.adapters.createPost.listeners.ICreatePostListener;
import ru.calloop.pikabu_demo.ui.utility.createPost.ItemMoveCallback;
import ru.calloop.pikabu_demo.adapters.createPost.holders.BaseViewHolder;
import ru.calloop.pikabu_demo.adapters.createPost.holders.TextViewHolder;
import ru.calloop.pikabu_demo.data.models.PostItem;
import ru.calloop.pikabu_demo.data.models.diffUtils.PostItemsDiffUtil;

public class CreatePostAdapter
        extends RecyclerView.Adapter<BaseViewHolder>
        implements ItemMoveCallback.ItemTouchHelperContract {

    private final Context context;
    private final List<PostItem> postItems;
    private final ICreatePostListener createPostListener;
    private boolean actionModeState;

    public CreatePostAdapter(Context context,
                             ICreatePostListener createPostListener) {
        this.context = context;
        this.createPostListener = createPostListener;
        postItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            CreatePostTextPostItemBinding binding =
                    CreatePostTextPostItemBinding.inflate(inflater, parent, false);
            return new TextViewHolder(binding, createPostListener);
        }
        throw new IllegalArgumentException("The view type value of $viewType is not supported");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(postItems.get(position), actionModeState);
    }

    //region [ITEM DATA]
    @Override
    public int getItemViewType(int position) {
        return postItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    @Override
    public long getItemId(int position) {
        return postItems.get(position).getId();
    }
    //endregion

    //region [ITEM MOVEMENT]
    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(postItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(postItems, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(@NonNull BaseViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(@NonNull BaseViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }
    //endregion

    //region [ADAPTER LIST OPERATIONS]
    public List<PostItem> getAdapterList() {
        return postItems;
    }

    public void setAdapterList(List<PostItem> newPostItems) {
        PostItemsDiffUtil postItemsDiffUtilCallback =
                new PostItemsDiffUtil(postItems, newPostItems);
        DiffUtil.DiffResult postItemsDiffResult =
                DiffUtil.calculateDiff(postItemsDiffUtilCallback);

        postItems.clear();
        postItems.addAll(newPostItems);
        postItemsDiffResult.dispatchUpdatesTo(this);
    }

    public void addItem(PostItem postItem) {
        postItems.add(postItem.getPosition(), postItem);
        notifyItemInserted(postItem.getPosition());
    }
    //endregion

    public void setActionModeState(boolean actionModeState) {
        this.actionModeState = actionModeState;
        notifyItemRangeChanged(0, postItems.size());
    }
}