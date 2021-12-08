package ru.calloop.pikabu_demo.mainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;

public class Tab1MainFragment extends Fragment implements MainContract.IView {
    private MainPresenter presenter;
    private BlocksListMainAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1_main, container, false);

        presenter = new MainPresenter();
        presenter.attachView(this);
        List<Post> postList = presenter.getAllPosts(0, 5);
        List<Integer> postIdList = new ArrayList<>();

        for (Post post :
                postList) {
            postIdList.add(post.getId());
        }
        adapter = new BlocksListMainAdapter(presenter.getPostItems(postIdList));

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.list_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}