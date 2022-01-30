package ru.calloop.pikabu_demo.ui.createPost.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.calloop.pikabu_demo.R;

public class CreatePostAddBlockFragment extends Fragment {

    Button buttonAddBlockCreatePost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_block_create_post, container, false);

        buttonAddBlockCreatePost = view.findViewById(R.id.button_add_block_create_post);


        buttonAddBlockCreatePost.setOnClickListener(v -> {
            //((CreatePostFragment) requireActivity()).buttonAddBlockCreatePost();
        });

        return view;
    }
}