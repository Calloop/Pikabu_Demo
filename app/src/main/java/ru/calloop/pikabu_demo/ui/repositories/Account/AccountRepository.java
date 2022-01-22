package ru.calloop.pikabu_demo.ui.repositories.Account;

import android.app.Application;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.signingActivity.models.Account;

public class AccountRepository implements IAccountRepository{
    private final IAccountDao AccountDao;

    @Inject
    public AccountRepository(Application application) {
        PikabuDB database = PikabuDB.getDatabase(application);
        AccountDao = database.getAccountDao();
    }

    @Override
    public String checkAccountExists(String login, String email){
        return AccountDao.checkAccountExists(login, email);
    }

    @Override
    public void createAccount(Account account)
    {
        AccountDao.createAccount(account);
    }

    public boolean checkLoginOrEmailExists(String loginOrEmail) {
        return AccountDao.checkLoginOrEmailExists(loginOrEmail);
    }

    public int checkPasswordIsCorrect(String password) {
        return AccountDao.checkPasswordIsCorrect(password);
    }
}
