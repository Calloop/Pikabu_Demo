package ru.calloop.pikabu_demo.ui.signing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.R;

public class SignUpFragment extends BaseFragment {

    @Override
    public BaseFragment providerFragment() {
        return new SignUpFragment();
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

//        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
//        assert actionBar != null;
//        actionBar.setDisplayHomeAsUpEnabled(true);

        Toolbar toolbar = new Toolbar(view.getContext());
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        actionBar.setTitle("Регистрация");

        return view;
    }
}
