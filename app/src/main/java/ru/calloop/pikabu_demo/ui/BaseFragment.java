package ru.calloop.pikabu_demo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public BaseFragment newInstance() {
        Bundle args = new Bundle();
        // args.putInt(ARG_PAGE, page);
        BaseFragment fragment = fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        return fragmentView(inflater, container, savedInstanseState);
    }

    public abstract BaseFragment fragment();

    public abstract View fragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
