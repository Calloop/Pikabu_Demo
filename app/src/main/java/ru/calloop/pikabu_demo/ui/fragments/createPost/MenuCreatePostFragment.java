package ru.calloop.pikabu_demo.ui.fragments.createPost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.calloop.pikabu_demo.databinding.CreatePostContentButtonsBinding;
import ru.calloop.pikabu_demo.viewmodels.CreatePostViewModel;
import ru.calloop.pikabu_demo.data.models.PostItem;

public class MenuCreatePostFragment extends Fragment implements View.OnClickListener {

    private CreatePostContentButtonsBinding binding;
    private CreatePostViewModel createPostViewModel;

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
        String warning = "Функция в разработке";

        if (id == binding.buttonAddTextCreatePost.getId()) {
            createPostItem(1);
        } else if (id == binding.buttonUseCameraCreatePost.getId()) {
            Toast.makeText(requireActivity(), warning, Toast.LENGTH_SHORT).show();
        } else if (id == binding.buttonAddImageCreatePost.getId()) {
            Toast.makeText(requireActivity(), warning, Toast.LENGTH_SHORT).show();
        } else if (id == binding.buttonAddVideoCreatePost.getId()) {
            Toast.makeText(requireActivity(), warning, Toast.LENGTH_SHORT).show();
        }
    }

    private void createPostItem(int type) {
        int position = createPostViewModel.getPostItems().isEmpty()
                ? 0 : createPostViewModel.getPostItems().size();
        createPostViewModel.setNewPostItem(new PostItem(type, position, null));
    }
}