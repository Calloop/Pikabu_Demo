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

public class SignInFragment extends BaseFragment {

    @Override
    public BaseFragment providerFragment() {
        return new SignInFragment();
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        Toolbar toolbar = new Toolbar(view.getContext());
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        actionBar.setTitle("Авторизация");

        return view;
    }

}
