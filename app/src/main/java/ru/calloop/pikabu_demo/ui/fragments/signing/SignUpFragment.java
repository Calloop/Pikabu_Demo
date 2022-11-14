package ru.calloop.pikabu_demo.ui.fragments.signing;

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

import java.text.MessageFormat;
import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.SignUpBinding;
import ru.calloop.pikabu_demo.data.repositories.Account.AccountRepository;
import ru.calloop.pikabu_demo.data.repositories.Account.IAccountDao;
import ru.calloop.pikabu_demo.data.repositories.Account.IAccountRepository;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    private SignUpBinding binding;
    private IAccountRepository accountRepository;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        accountRepository = new AccountRepository(activity);
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle("Регистрация");

        activity.addMenuProvider(
                new menuProvider(), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        navController = NavHostFragment.findNavController(this);

        binding.buttonGoToSignIn.setOnClickListener(this);
        binding.buttonTryToRegister.setOnClickListener(this);
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
        if (id == binding.buttonGoToSignIn.getId()) {
            navController.popBackStack(R.id.signInFragment, false);
        } else if (id == binding.buttonTryToRegister.getId()) {
            int passwordMinLength = 5;

            if (FieldsAreFilled() && IsPasswordMinLength(passwordMinLength)
                    && IsIdenticalPasswords() && IsNewAccount()) {
                String login = String.valueOf(binding.editTextLogin.getText());
                String email = String.valueOf(binding.editTextEmail.getText());
                String password1 = String.valueOf(binding.editTextPassword1.getText());

                accountRepository.createAccount(login, email, password1);
                navController.popBackStack(R.id.homeFragment, false);
            }
        }
    }

    private boolean FieldsAreFilled() {
        EditText[] fields = {binding.editTextLogin, binding.editTextEmail,
                binding.editTextPassword1, binding.editTextPassword2};
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
        String password1 = String.valueOf(binding.editTextPassword1.getText());

        if (password1.length() >= passwordMinLength) {
            return true;
        } else {
            String errorMessage = MessageFormat
                    .format("Длина пароля должна быть минимум {0} символов",
                            passwordMinLength);
            binding.editTextPassword1.setError(errorMessage);
            return false;
        }
    }

    private boolean IsIdenticalPasswords() {
        String password1 = String.valueOf(binding.editTextPassword1.getText());
        String password2 = String.valueOf(binding.editTextPassword2.getText());

        if (!password1.equals(password2)) {
            binding.editTextPassword1.setError("Пароли не совпадают");
            return false;
        } else
            return true;
    }

    private boolean IsNewAccount() {
        String login = String.valueOf(binding.editTextLogin.getText());
        String email = String.valueOf(binding.editTextEmail.getText());
        String result = accountRepository.checkAccountExists(login, email);

        if (result != null) {
            if (result.equals(IAccountDao.LOGIN)) {
                binding.editTextLogin.setError("Логин уже существует");
                return false;
            } else if (result.equals(IAccountDao.EMAIL)) {
                binding.editTextEmail.setError("Email уже существует");
                return false;
            } else return true;
        } else {
            return true;
        }
    }
}
