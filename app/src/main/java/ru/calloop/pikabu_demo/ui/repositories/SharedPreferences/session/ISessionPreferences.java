package ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session;

public interface ISessionPreferences {
    void startUserSession(int accountId);

    int getAccountId();

    boolean sessionStarted();

    void endUserSession();
}
