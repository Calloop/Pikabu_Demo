package ru.calloop.pikabu_demo.ui.main.home.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.databinding.MainTab1Binding;
import ru.calloop.pikabu_demo.ui.main.adapters.PostsAdapter;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;

public class Tab1MainFragment extends Fragment implements PostsAdapter.PostsAdapterCallback {

    private MainTab1Binding binding;
    private HomeViewModel homeViewModel;
    private PostsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MainTab1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new PostsAdapter();
        adapter.registerCallBack(this);
        homeViewModel.getPosts().observe(getViewLifecycleOwner(), adapter::updateList);
        setPostRecycler();
    }

    private void setPostRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = binding.listMain;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        homeViewModel.setCachedPosts(adapter.getAdapterList());
    }

    @Override
    public void getViewedPostId(int id) {

    }

    @Override
    public void detached(int id) {

    }
}