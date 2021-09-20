package ru.calloop.pikabu_demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.calloop.pikabu_demo.R;

public class ListItemsAdapterCreatePost extends RecyclerView.Adapter<ListItemsAdapterCreatePost.ItemHolder> {

    private final int TYPE_ITEM1 = 1;
    private final int TYPE_ITEM2 = 2;
    ArrayList<ListItemCreatePost> items;

    public ListItemsAdapterCreatePost(ArrayList<ListItemCreatePost> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;

        if (viewType == TYPE_ITEM1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_add_block_text_create_post, parent, false);
        } else if (viewType == TYPE_ITEM2) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_add_block_photo_create_post, parent, false);
        }
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        int type = getItemViewType(position);

        if (type == TYPE_ITEM1) {
            TypeA typeA = (TypeA) items.get(position);
        }
        if (type == TYPE_ITEM2) {
            TypeA typeA = (TypeA) items.get(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = items.get(position).getItemType();
        if (type == 1) return TYPE_ITEM1;
        else return TYPE_ITEM2;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        TextView textBlock, photoBlock;

        ItemHolder(View itemView) {
            super(itemView);
            textBlock = itemView.findViewById(R.id.text_block);
            photoBlock = itemView.findViewById(R.id.photo_block);
        }
    }
}