package ru.calloop.pikabu_demo.createPostPage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostPage.Model.PostData;

public class BlocksListCreatePostAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private List<PostData> data = new ArrayList<>();;
    private OnItemClickListener listener;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public BlocksListCreatePostAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT_BLOCK) {
            return new TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_text_block_create_post, parent, false));
        } else if (viewType == TYPE_IMAGE_BLOCK) {
            return new TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_image_block_create_post, parent, false));
        } else {
            return new AddBlockViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_add_block_create_post, parent, false));
        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_TEXT_BLOCK) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
        }

        // тут создавать холдер и с ним делать действия типа нажатий
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getDataType() == 1) {
            return TYPE_TEXT_BLOCK;
        } else if (data.get(position).getDataType() == 2) {
            return TYPE_IMAGE_BLOCK;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AddBlockViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Button button;

        public AddBlockViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.button_add_block_create_post);

            button.setOnClickListener(view1 -> {
                Toast.makeText(view1.getContext(), "CLICKED", Toast.LENGTH_SHORT).show();
            });

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.editText_textBlock_createPost);
        }
    }

    public void createBlock(int type) {
        PostData emptyData = new PostData(0);
        PostData postData = new PostData(type);

        if (data.size() == 0) {
            data.add(emptyData);
        }

        data.add(postData);
        data.add(emptyData);
        notifyItemChanged(postData.getDataPosition());
        notifyItemChanged(emptyData.getDataPosition());
    }

    public boolean deleteBlock() {
        int counter = 0;

        if (data.size() > 0) {
            counter = 2;

            if (data.size() == 3) {
                counter = 3;
            }

            for (int i = 0; i < counter; i++) {
                data.remove(data.size() - 1);
            }
// или записать последнюю запись и удалить
            notifyItemRemoved(data.size() - 1);
            return true;
        } else return false;
    }
}