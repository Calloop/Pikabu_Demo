package ru.calloop.pikabu_demo.createPostActivity.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.ui.createPost.CreatePostFragment;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;

public class MenuCreatePostFragment extends BaseFragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    @Override
    public BaseFragment providerFragment() {
        return new MenuCreatePostFragment();
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_buttons_create_post,
                container, false);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        Button buttonAddTextCreatePost = view.findViewById(R.id.button_add_text_create_post);
        Button buttonAddImageCreatePost = view.findViewById(R.id.button_add_image_create_post);
        Button buttonDeleteBlockCreatePost = view
                .findViewById(R.id.button_delete_block_create_post);

        buttonAddTextCreatePost.setOnClickListener(this);
        buttonAddImageCreatePost.setOnClickListener(this);
        buttonDeleteBlockCreatePost.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_add_text_create_post) {
            homeViewModel.setState(1);
        } else if (id == R.id.button_add_image_create_post) {
            homeViewModel.setState(2);
        } else if (id == R.id.button_delete_block_create_post) {
            homeViewModel.setState(3);
        }
    }
}