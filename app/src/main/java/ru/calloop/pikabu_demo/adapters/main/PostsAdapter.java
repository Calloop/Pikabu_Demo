package ru.calloop.pikabu_demo.adapters.main;

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

import ru.calloop.pikabu_demo.adapters.main.holders.PostViewHolder;
import ru.calloop.pikabu_demo.databinding.MainPostBinding;
import ru.calloop.pikabu_demo.data.models.PostWithPostItems;
import ru.calloop.pikabu_demo.data.models.diffUtils.PostsDiffUtil;

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder> {

    public interface PostsAdapterCallback {
        void getViewedPostId(int id);

        void detached(int id);
    }

    RecyclerView.RecycledViewPool sharedPool;
    private PostsAdapterCallback postsAdapterCallback;
    private List<PostWithPostItems> posts;

    public PostsAdapter() {
        posts = new ArrayList<>(1);
    }

//    public void registerCallBack(PostsAdapterCallback postsAdapterCallback) {
//        this.postsAdapterCallback = postsAdapterCallback;
//    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MainPostBinding binding = MainPostBinding
                .inflate(inflater, parent, false);
        return new PostViewHolder(binding, setPostItemsRecycler(binding));
    }

    @NonNull
    private RecyclerView setPostItemsRecycler(@NonNull MainPostBinding binding) {
        RecyclerView recyclerView = binding.mainPostContent;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        sharedPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(sharedPool);
        return recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(posts.get(position));
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
    public void onViewAttachedToWindow(@NonNull PostViewHolder holder) {
        Timer postWasViewed = new Timer();
        postWasViewed.schedule(new TimerTask() {
            @Override
            public void run() {
                //postsAdapterCallback.getViewedPostId(holder.getBindingAdapterPosition());
            }
        }, 2000);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PostViewHolder holder) {
        //postsAdapterCallback.detached(holder.getBindingAdapterPosition());
    }
}