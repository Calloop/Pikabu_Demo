package ru.calloop.pikabu_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

import java.util.Objects;

public class ContentButtonsCreatePostFragment extends Fragment {

    Button buttonAddTextCreatePost, buttonAddPhotoCreatePost, buttonDeleteBlockCreatePost,
            buttonAddBlockCreatePost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_buttons_create_post, container, false);

        buttonAddTextCreatePost = view.findViewById(R.id.button_add_text_create_post);
        buttonAddPhotoCreatePost = view.findViewById(R.id.button_add_photo_create_post);
        buttonDeleteBlockCreatePost = view.findViewById(R.id.button_delete_block_create_post);

        buttonAddTextCreatePost.setOnClickListener(v -> {
            ((CreatePostActivity) requireActivity()).ButtonAddTextCreatePostClicked();
        });

        buttonAddPhotoCreatePost.setOnClickListener(v -> {
            ((CreatePostActivity) requireActivity()).ButtonAddPhotoCreatePostClicked();
        });

        buttonDeleteBlockCreatePost.setOnClickListener(v -> {
            ((CreatePostActivity) requireActivity()).ButtonDeleteBlockCreatePostClicked();
        });

        return view;
    }
}