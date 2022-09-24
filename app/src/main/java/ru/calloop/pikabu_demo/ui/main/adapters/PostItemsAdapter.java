package ru.calloop.pikabu_demo.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.MainPostItemBinding;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class PostItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostItem> postItems;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

    public PostItemsAdapter() {
        postItems = new ArrayList<>(1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MainPostItemBinding binding =
                MainPostItemBinding.inflate(inflater, parent, false);
        return new TextViewHolder(binding);

//        if (viewType == TYPE_TEXT_BLOCK) {
//            FragmentMainPostItemBinding binding =
//                    FragmentMainPostItemBinding.inflate(inflater, parent, false);
//            return new TextViewHolder(binding);
//        } else {
//            return new ImageViewHolder(LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.fragment_main_post_item, parent, false));
//        }
        // ДОБАВИТЬ ОКНО С ОШИБКОЙ ЗАГРУЗКИ ДАННЫХ
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        TextViewHolder textHolder = (TextViewHolder) viewHolder;
        textHolder.binding.postItemBody.setText(postItems.get(position).getValue());

//        if (viewHolder.getItemViewType() == TYPE_TEXT_BLOCK) {
//            TextViewHolder textHolder = (TextViewHolder) viewHolder;
//            textHolder.getTextView(postItems.get(position).getValue());
//        } else if (viewHolder.getItemViewType() == TYPE_IMAGE_BLOCK) {
//            ImageViewHolder imageHolder = (ImageViewHolder) viewHolder;
//            imageHolder.getTextView(postItems.get(position).getValue());
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (postItems.get(position).getType() == 1) {
            return TYPE_TEXT_BLOCK;
        } else {
            return TYPE_IMAGE_BLOCK;
        }
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        private final MainPostItemBinding binding;

        public TextViewHolder(@NonNull MainPostItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        //private final EditText editText;

        public ImageViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.post_item_body);
            //editText = view.findViewById(R.id.editText_textBlock_main);
        }

        void getTextView(String text) {
            textView.setText(text);
            //editText.setText(text);
        }
    }

    public void updateList(List<PostItem> postItems) {
        this.postItems = postItems;
        notifyItemRangeChanged(0, this.postItems.size());
    }
}

