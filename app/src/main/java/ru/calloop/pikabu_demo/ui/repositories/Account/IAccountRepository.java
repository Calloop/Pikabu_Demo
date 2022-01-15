package ru.calloop.pikabu_demo.ui.repositories.Account;

import ru.calloop.pikabu_demo.signingActivity.models.Account;

public interface IAccountRepository {
    String checkAccountExists(String login, String email);
    void createAccount(Account account);
}
