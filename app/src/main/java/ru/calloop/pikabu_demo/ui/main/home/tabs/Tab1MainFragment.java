package ru.calloop.pikabu_demo.ui.main.home.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.FragmentTab1MainBinding;
import ru.calloop.pikabu_demo.ui.BaseFragment;
import ru.calloop.pikabu_demo.ui.main.adapters.PostsAdapter;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;

public class Tab1MainFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    private PostsAdapter adapter;
    private FragmentTab1MainBinding binding;

    @Override
    public BaseFragment fragment() {
        return newInstance();
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTab1MainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new PostsAdapter();
        homeViewModel.getPosts().observe(getViewLifecycleOwner(), adapter::updateList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        RecyclerView recyclerView = root.findViewById(R.id.list_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return root;
    }
}