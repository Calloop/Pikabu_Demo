package ru.calloop.pikabu_demo.ui.createPost;

import android.text.Editable;
import android.text.TextWatcher;

public class CreatePostListener implements TextWatcher {
    private int position;

    public void updatePosition(int position) {
        this.position = position;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        // no op
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        //mDataset[position] = charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
