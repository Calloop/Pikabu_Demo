package ru.calloop.pikabu_demo.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.databinding.MainImagePostItemBinding;
import ru.calloop.pikabu_demo.databinding.MainTextPostItemBinding;
import ru.calloop.pikabu_demo.ui.createPost.adapters.holders.BaseViewHolder;
import ru.calloop.pikabu_demo.ui.main.adapters.holders.ImageViewHolder;
import ru.calloop.pikabu_demo.ui.main.adapters.holders.TextViewHolder;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class PostItemsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<PostItem> postItems;

    private static final int TYPE_TEXT_BLOCK = 1;
    private static final int TYPE_IMAGE_BLOCK = 2;

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

//    public static class TextViewHolder extends RecyclerView.ViewHolder {
//        private final MainPostItemBinding binding;
//
//        public TextViewHolder(@NonNull MainPostItemBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//    }
//
//    public static class ImageViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;
//        //private final EditText editText;
//
//        public ImageViewHolder(View view) {
//            super(view);
//            textView = view.findViewById(R.id.post_item_body);
//            //editText = view.findViewById(R.id.editText_textBlock_main);
//        }
//
//        void getTextView(String text) {
//            textView.setText(text);
//            //editText.setText(text);
//        }
//    }

    public void updateList(List<PostItem> postItems) {
        this.postItems = postItems;
        notifyItemRangeChanged(0, this.postItems.size());
    }
}

