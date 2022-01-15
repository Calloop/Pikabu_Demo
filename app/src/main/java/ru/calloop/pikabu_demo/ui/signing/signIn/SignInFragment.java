package ru.calloop.pikabu_demo.ui.signing.signIn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.signingActivity.models.SessionManager;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;

public class SignInFragment extends BaseFragment implements View.OnClickListener {

    private SignInViewModel signInViewModel;
    private NavController navController;
    private EditText editTextLoginSignIn, editTextPasswordSignIn;

    @Override
    public BaseFragment providerFragment() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View providerFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        editTextLoginSignIn = view.findViewById(R.id.editTextLogin_signIn);
        editTextPasswordSignIn = view.findViewById(R.id.editTextPassword_signIn);

        Button buttonSignUp = view.findViewById(R.id.buttonSignUp);
        Button buttonSendLoginData = view.findViewById(R.id.buttonSendLoginData);

        buttonSignUp.setOnClickListener(this);
        buttonSendLoginData.setOnClickListener(this);

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        Toolbar toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("Авторизация");

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.activity_navigation_controller);
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        signInViewModel = new ViewModelProvider(requireActivity()).get(SignInViewModel.class);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_authorization, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonSendLoginData) {
            String loginOrEmail = editTextLoginSignIn.getText().toString();
            String password = editTextPasswordSignIn.getText().toString();

            int accountId = signInViewModel.doLogin(loginOrEmail, password);
            if (accountId != 0) {
                SessionManager sessionManager = new SessionManager(requireContext());
                if (!sessionManager.isSessionActive()) {
                    sessionManager.startUserSession(accountId);
                }
            }

        } else if (id == R.id.buttonSignUp) {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment);
        }
    }
}
