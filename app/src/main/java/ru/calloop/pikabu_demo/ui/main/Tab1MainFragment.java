package ru.calloop.pikabu_demo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.main.mainActivity.MainContract;
import ru.calloop.pikabu_demo.ui.main.mainActivity.MainPresenter;
import ru.calloop.pikabu_demo.ui.main.mainActivity.adapters.BlocksListMainAdapter;

public class Tab1MainFragment extends BaseFragment implements MainContract.IView {
    private MainPresenter presenter;
    private BlocksListMainAdapter adapter;

    @Override
    public BaseFragment providerFragment() {
        return new Tab1MainFragment();
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1_main, container, false);

        presenter = new MainPresenter();
        presenter.attachView(this);
        adapter = new BlocksListMainAdapter(presenter.getPostItems(0, 5));

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.list_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}