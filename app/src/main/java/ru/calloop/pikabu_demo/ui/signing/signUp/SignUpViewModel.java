package ru.calloop.pikabu_demo.ui.signing.signUp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ru.calloop.pikabu_demo.ui.signing.models.Account;
import ru.calloop.pikabu_demo.ui.repositories.Account.AccountRepository;

public class SignUpViewModel extends AndroidViewModel {
    private final AccountRepository repository;
//    private final LiveData<Account> account;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository(application);
    }

//    LiveData<Account> getAccount() { return account; }

    public void createAccount(String login, String email, String password) {
        Account account = new Account(login, email, password);
        repository.createAccount(account);
    }

    public String checkAccountExists(String login, String email){
        return repository.checkAccountExists(login, email);
    }
}
