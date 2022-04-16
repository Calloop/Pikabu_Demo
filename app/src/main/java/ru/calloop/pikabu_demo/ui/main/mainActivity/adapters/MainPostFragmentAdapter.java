package ru.calloop.pikabu_demo.ui.main.mainActivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;

public class MainPostFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<PostItem> postItemList;
    private Context context;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public MainPostFragmentAdapter(List<PostItem> postItemList) {
        this.postItemList = postItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT_BLOCK) {
            return new TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_main_post_item, parent, false));
        } else {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_main_post_item, parent, false));
        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == TYPE_TEXT_BLOCK) {
            TextViewHolder textHolder = (TextViewHolder) viewHolder;
            textHolder.getTextView(postItemList.get(position).getDataValue());
        } else if (viewHolder.getItemViewType() == TYPE_IMAGE_BLOCK) {
            ImageViewHolder imageHolder = (ImageViewHolder) viewHolder;
            imageHolder.getTextView(postItemList.get(position).getDataValue());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (postItemList.get(position).getDataType() == 1) {
            return TYPE_TEXT_BLOCK;
        } else {
            return TYPE_IMAGE_BLOCK;
        }
    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        //private final EditText editText;

        public TextViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewPostItem);
            //editText = view.findViewById(R.id.editText_textBlock_main);
        }

        void getTextView(String text) {
            textView.setText(text);
            //editText.setText(text);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        //private final EditText editText;

        public ImageViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewPostItem);
            //editText = view.findViewById(R.id.editText_textBlock_main);
        }

        void getTextView(String text) {
            textView.setText(text);
            //editText.setText(text);
        }
    }
}

