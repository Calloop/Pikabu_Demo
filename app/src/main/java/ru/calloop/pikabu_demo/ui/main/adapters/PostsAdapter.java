package ru.calloop.pikabu_demo.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.FragmentMainPostBinding;
import ru.calloop.pikabu_demo.ui.models.Post;
import ru.calloop.pikabu_demo.ui.models.PostAndPostItem;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<PostAndPostItem> posts;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public PostsAdapter() {
        posts = new ArrayList<>(1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentMainPostBinding binding = FragmentMainPostBinding
                .inflate(inflater, parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PostItemsAdapter adapter = new PostItemsAdapter();
        adapter.updateList(posts.get(position).postItemList);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(((ViewHolder) viewHolder).recyclerView.getContext());
        ((ViewHolder) viewHolder).recyclerView.setLayoutManager(layoutManager);
        ((ViewHolder) viewHolder).recyclerView.setAdapter(adapter);
        if (viewHolder.binding != null) {
            viewHolder.postHeadline.setText(posts.get(position).post.getHeadline());
        }
        //Post post = posts.get(position).post;

//        ((ViewHolder) viewHolder).binding.postHeadline.
//                setText(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;
        private final TextView postHeadline;
        private final FragmentMainPostBinding binding;

        public ViewHolder(View view) {
            super(view.getRootView());
            binding = DataBindingUtil.bind(view.getRootView());
            recyclerView = view.findViewById(R.id.list_main_post_item);
            postHeadline = view.findViewById(R.id.post_headline);
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }

    public void updateList(List<PostAndPostItem> posts) {
        if (this.posts == null) {
            this.posts = posts;
            notifyItemRangeInserted(0, posts.size());
        } else {
            PostsDiffUtil diffUtilCallback = new PostsDiffUtil(this.posts, posts);
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffUtilCallback);
            this.posts = posts;
            result.dispatchUpdatesTo(this);
        }
    }

    public List<PostAndPostItem> getAdapterList() {
        return posts;
    }
}