package ru.calloop.pikabu_demo.ui.signing.signUp;

import android.os.Bundle;
import android.text.TextUtils;
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

import java.text.MessageFormat;
import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.ui.repositories.Account.IAccountDao;

public class SignUpFragment extends BaseFragment implements View.OnClickListener {

    private SignUpViewModel signUpViewModel;
    private NavController navController;
    private EditText editTextLogin, editTextEmail, editTextPassword1, editTextPassword2;

    @Override
    public BaseFragment providerFragment() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View providerFragmentView
            (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword1 = view.findViewById(R.id.editTextPassword1);
        editTextPassword2 = view.findViewById(R.id.editTextPassword2);

        Button buttonGoToSignIn = view.findViewById(R.id.buttonGoToSignIn);
        Button buttonSignUpCreateAccount = view.findViewById(R.id.buttonTryToRegister);

        buttonGoToSignIn.setOnClickListener(this);
        buttonSignUpCreateAccount.setOnClickListener(this);

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        Toolbar toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("Регистрация");

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.activity_navigation_controller);
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);

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
        if (id == R.id.buttonGoToSignIn) {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment);
        } else if (id == R.id.buttonTryToRegister) {
            int passwordMinLength = 5;

            if (FieldsAreFilled() && IsPasswordMinLength(passwordMinLength)
                    && IsIdenticalPasswords() && IsNewAccount()) {
                String login = editTextLogin.getText().toString();
                String email = editTextEmail.getText().toString();
                String password1 = editTextPassword1.getText().toString();

                signUpViewModel.createAccount(login, email, password1);
                navController.navigate(R.id.action_signUpFragment_to_homeFragment);
            }
        }
    }

    private boolean FieldsAreFilled() {
        EditText[] fields = {editTextLogin, editTextEmail, editTextPassword1, editTextPassword2};
        int errorCounter = 0;

        for (EditText field :
                fields) {
            if (TextUtils.isEmpty(field.getText())) {
                field.setError("Заполните поле");
                errorCounter++;
            }
        }

        return errorCounter == 0;
    }

    private boolean IsPasswordMinLength(int passwordMinLength) {
        String password1 = editTextPassword1.getText().toString();

        if (password1.length() >= passwordMinLength) {
            return true;
        } else {
            String errorMessage = MessageFormat
                    .format("Длина пароля должна быть минимум {0} символов",
                            passwordMinLength);
            editTextPassword1.setError(errorMessage);
            return false;
        }
    }

    private boolean IsIdenticalPasswords() {
        String password1 = editTextPassword1.getText().toString();
        String password2 = editTextPassword2.getText().toString();

        if (!password1.equals(password2)) {
            editTextPassword1.setError("Пароли не совпадают");
            return false;
        } else
            return true;
    }

    private boolean IsNewAccount() {
        String login = editTextLogin.getText().toString();
        String email = editTextEmail.getText().toString();
        String result = signUpViewModel.checkAccountExists(login, email);

        if (result != null) {
            if (result.equals(IAccountDao.LOGIN)) {
                editTextLogin.setError("Логин уже существует");
                return false;
            } else if (result.equals(IAccountDao.EMAIL)) {
                editTextEmail.setError("Email уже существует");
                return false;
            } else return true;
        } else {
            return true;
        }
    }
}
