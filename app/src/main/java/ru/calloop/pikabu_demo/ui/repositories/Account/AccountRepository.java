package ru.calloop.pikabu_demo.ui.repositories.Account;

import android.content.Context;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.models.Account;

public class AccountRepository implements IAccountRepository{
    private final IAccountDao AccountDao;

    @Inject
    public AccountRepository(Context context) {
        PikabuDB database = PikabuDB.getDatabase(context);
        AccountDao = database.getAccountDao();
    }

    @Override
    public String checkAccountExists(String login, String email){
        return AccountDao.checkAccountExists(login, email);
    }

    @Override
    public void createAccount(String login, String email, String password)
    {
        Account account = new Account(login, email, password);
        AccountDao.createAccount(account);
    }

    @Override
    public boolean checkLoginOrEmailExists(String loginOrEmail) {
        return AccountDao.checkLoginOrEmailExists(loginOrEmail);
    }

    @Override
    public int checkPasswordIsCorrect(String password) {
        return AccountDao.checkPasswordIsCorrect(password);
    }
}
