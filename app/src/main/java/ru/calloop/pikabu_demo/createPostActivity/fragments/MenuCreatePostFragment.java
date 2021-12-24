package ru.calloop.pikabu_demo.createPostActivity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.ui.createPost.CreatePostFragment;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;

public class MenuCreatePostFragment extends BaseFragment implements View.OnClickListener {

    private HomeViewModel model;
    private Button buttonAddTextCreatePost, buttonAddImageCreatePost, buttonDeleteBlockCreatePost;

    @Override
    public BaseFragment providerFragment() {
        return new MenuCreatePostFragment();
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_buttons_create_post, container, false);

        model = new ViewModelProvider(this.requireActivity()).get(HomeViewModel.class);

        buttonAddTextCreatePost = view.findViewById(R.id.button_add_text_create_post);
        buttonAddImageCreatePost = view.findViewById(R.id.button_add_image_create_post);
        buttonDeleteBlockCreatePost = view.findViewById(R.id.button_delete_block_create_post);

        buttonAddTextCreatePost.setOnClickListener(this);
        buttonAddImageCreatePost.setOnClickListener(this);
        buttonDeleteBlockCreatePost.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_add_text_create_post) {
            model.setState(1);
        } else if (id == R.id.button_add_image_create_post) {
            model.setState(2);
        } else if (id == R.id.button_delete_block_create_post) {
            model.setState(3);
        }
    }
}