package ru.calloop.pikabu_demo.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public BaseFragment newInstance() {
        BaseFragment fragment = providerFragment();
        return fragment;

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanseState) {
        View view = providerFragmentView(inflater, container, savedInstanseState);
        return view;
    }

    public abstract BaseFragment providerFragment();

    public abstract View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
