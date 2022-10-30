package ru.calloop.pikabu_demo.ui.main.adapters.holders;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.MainImagePostItemBinding;
import ru.calloop.pikabu_demo.ui.createPost.adapters.holders.BaseViewHolder;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class ImageViewHolder extends BaseViewHolder {

    private final ImageView content;

    public ImageViewHolder(@NonNull MainImagePostItemBinding binding) {
        super(binding);
        content = binding.mainImageTypeContent;
    }

    @Override
    public void bind(PostItem postItem) {
        super.bind(postItem);

        Picasso.get()
                .load("file://" + postItem.getValue())
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .centerInside()
                .into(content);
    }
}
