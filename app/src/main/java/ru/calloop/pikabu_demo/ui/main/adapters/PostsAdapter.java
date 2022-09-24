package ru.calloop.pikabu_demo.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.calloop.pikabu_demo.databinding.MainPostBinding;
import ru.calloop.pikabu_demo.ui.models.PostWithPostItems;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public interface PostsAdapterCallback {
        void getViewedPostId(int id);

        void detached(int id);
    }

    private PostsAdapterCallback postsAdapterCallback;
    private List<PostWithPostItems> posts;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public PostsAdapter() {
        posts = new ArrayList<>(1);
    }

    public void registerCallBack(PostsAdapterCallback postsAdapterCallback) {
        this.postsAdapterCallback = postsAdapterCallback;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MainPostBinding binding;
        private PostItemsAdapter adapter;

        public ViewHolder(@NonNull MainPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            setPostItemsRecycler();
        }

        private void setPostItemsRecycler() {
            RecyclerView recyclerView = binding.mainPostContent;
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            adapter = new PostItemsAdapter();
            recyclerView.setAdapter(adapter);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MainPostBinding binding = MainPostBinding
                .inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PostWithPostItems postWithPostItems = posts.get(position);
        viewHolder.adapter.updateList(postWithPostItems.postItemList);
        viewHolder.binding.mainPostHeadline.setText(postWithPostItems.post.getHeadline());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void updateList(List<PostWithPostItems> posts) {
        PostsDiffUtil diffUtilCallback = new PostsDiffUtil(this.posts, posts);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffUtilCallback);
        this.posts = posts;
        result.dispatchUpdatesTo(this);
    }

    public List<PostWithPostItems> getAdapterList() {
        return posts;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        Timer postWasViewed = new Timer();
        postWasViewed.schedule(new TimerTask() {
            @Override
            public void run() {
                postsAdapterCallback.getViewedPostId(holder.getAdapterPosition());
            }
        }, 2000);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        postsAdapterCallback.detached(holder.getAdapterPosition());
    }
}