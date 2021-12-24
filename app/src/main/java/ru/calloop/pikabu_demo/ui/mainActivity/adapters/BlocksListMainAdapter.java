package ru.calloop.pikabu_demo.ui.mainActivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.models.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;

public class BlocksListMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<PostAndPostItem> postList;
    public List<PostItem> postItemList = new ArrayList<>();
    private Context context;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListMainAdapter(List<PostAndPostItem> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_main_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        MainPostFragmentAdapter adapter = new MainPostFragmentAdapter(postList.get(position).postItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.recyclerView.getContext());
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.list_main_post_item);
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }
}