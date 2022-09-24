package ru.calloop.pikabu_demo.ui.signing;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.SignInBinding;
import ru.calloop.pikabu_demo.ui.repositories.Account.AccountRepository;
import ru.calloop.pikabu_demo.ui.repositories.Account.IAccountRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.SessionPreferences;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private SignInBinding binding;
    private IAccountRepository accountRepository;
    private NavController navController;
    private ISessionPreferences sessionPreferenceRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Авторизация");

        sessionPreferenceRepository = new SessionPreferences(activity);
        accountRepository = new AccountRepository(activity);
        activity.addMenuProvider(
                new menuProvider(), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        navController = NavHostFragment.findNavController(this);

        binding.buttonSignUp.setOnClickListener(this);
        binding.buttonSendLoginData.setOnClickListener(this);
    }

    private static class menuProvider implements MenuProvider {
        @Override
        public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.toolbar_authorization, menu);
        }

        @Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();

            return false;
        }
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();

        if (id == binding.buttonSendLoginData.getId()) {
            String loginOrEmail = String.valueOf(binding.editTextLoginSignIn.getText());
            String password = String.valueOf(binding.editTextPasswordSignIn.getText());

            if (FieldsAreFilled()) {
                if (accountRepository.checkLoginOrEmailExists(loginOrEmail)
                        && !sessionPreferenceRepository.sessionStarted()) {
                    int accountId = accountRepository.checkPasswordIsCorrect(password);

                    if (accountId != 0) {
                        sessionPreferenceRepository.startUserSession(accountId);
                        navController.popBackStack(R.id.homeFragment, false);
                    } else {
                        binding.editTextPasswordSignIn.setError("Неверный пароль");
                    }
                } else {
                    binding.editTextLoginSignIn.setError("Пользователь не существует");
                }
            }
        } else if (id == binding.buttonSignUp.getId()) {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment);
        }
    }

    private boolean FieldsAreFilled() {
        EditText[] fields = {binding.editTextLoginSignIn, binding.editTextPasswordSignIn};
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
}
