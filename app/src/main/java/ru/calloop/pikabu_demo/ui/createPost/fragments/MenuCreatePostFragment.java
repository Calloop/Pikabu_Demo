package ru.calloop.pikabu_demo.ui.createPost.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.calloop.pikabu_demo.databinding.CreatePostContentButtonsBinding;
import ru.calloop.pikabu_demo.ui.createPost.CreatePostViewModel;
import ru.calloop.pikabu_demo.ui.createPost.adapters.listeners.ICreatePostListener;
import ru.calloop.pikabu_demo.ui.createPost.adapters.listeners.ITestListener;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class MenuCreatePostFragment extends Fragment implements View.OnClickListener {

    private CreatePostContentButtonsBinding binding;
    private CreatePostViewModel createPostViewModel;
    private ICreatePostListener createPostListener;
    private ITestListener testListener;
    public int test;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CreatePostContentButtonsBinding.inflate(inflater, container, false);
        binding.buttonAddTextCreatePost.setOnClickListener(this);
        binding.buttonUseCameraCreatePost.setOnClickListener(this);
        binding.buttonAddImageCreatePost.setOnClickListener(this);
        binding.buttonAddVideoCreatePost.setOnClickListener(this);
        createPostListener = new CreatePostFragment();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createPostViewModel =
                new ViewModelProvider(requireActivity()).get(CreatePostViewModel.class);
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();

        if (id == binding.buttonAddTextCreatePost.getId()) {
            createPostItem(1);
        } else if (id == binding.buttonUseCameraCreatePost.getId()) {
            createPostItem(2);
        } else if (id == binding.buttonAddImageCreatePost.getId()) {
            createPostItem(3);
        } else if (id == binding.buttonAddVideoCreatePost.getId()) {
            createPostItem(4);
        }
    }

    // убрать else?
    private void createPostItem(int type) {
        if (createPostViewModel.getPostItems().isEmpty()) {
            createPostViewModel.setNewPostItem(new PostItem(type, 0, null));
            Log.d("TAG", "createPostItem: " + type);
        } else {
            createPostViewModel.setNewPostItem(new PostItem(type,
                    createPostViewModel.getPostItems().size(), null));
            Log.d("TAG", "createPostItem2: ");
        }
    }
}