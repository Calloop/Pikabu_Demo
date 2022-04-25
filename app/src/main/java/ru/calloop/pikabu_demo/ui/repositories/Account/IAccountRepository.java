package ru.calloop.pikabu_demo.ui.repositories.Account;

public interface IAccountRepository {
    String checkAccountExists(String login, String email);

    void createAccount(String login, String email, String password);

    boolean checkLoginOrEmailExists(String loginOrEmail);

    int checkPasswordIsCorrect(String password);
}
