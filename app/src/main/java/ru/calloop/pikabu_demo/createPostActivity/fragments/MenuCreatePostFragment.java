package ru.calloop.pikabu_demo.createPostActivity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostActivity;

public class MenuCreatePostFragment extends Fragment {

    Button buttonAddTextCreatePost, buttonAddImageCreatePost, buttonDeleteBlockCreatePost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_buttons_create_post, container, false);

        buttonAddTextCreatePost = view.findViewById(R.id.button_add_text_create_post);
        buttonAddImageCreatePost = view.findViewById(R.id.button_add_image_create_post);
        buttonDeleteBlockCreatePost = view.findViewById(R.id.button_delete_block_create_post);

        buttonAddTextCreatePost.setOnClickListener(v -> {
            ((CreatePostActivity) requireActivity()).buttonAddTextCreatePostClicked();
        });

        buttonAddImageCreatePost.setOnClickListener(v -> {
            ((CreatePostActivity) requireActivity()).buttonAddImageCreatePostClicked();
        });

        buttonDeleteBlockCreatePost.setOnClickListener(v -> {
            ((CreatePostActivity) requireActivity()).buttonDeleteBlockCreatePostClicked();
        });

        return view;
    }
}