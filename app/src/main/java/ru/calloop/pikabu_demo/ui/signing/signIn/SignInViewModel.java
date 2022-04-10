package ru.calloop.pikabu_demo.ui.signing.signIn;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ru.calloop.pikabu_demo.ui.repositories.Account.AccountRepository;

public class SignInViewModel extends AndroidViewModel {
    private final AccountRepository repository;
//    private LiveData<Account> account;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository(application);
    }

//    LiveData<Account> getAccount() {
//        return account;
//    }

    public boolean checkLoginOrEmailExists(String loginOrEmail) {
        return repository.checkLoginOrEmailExists(loginOrEmail);
    }

    public int checkPasswordIsCorrect(String password) {
        return repository.checkPasswordIsCorrect(password);
    }
}
