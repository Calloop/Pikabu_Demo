package ru.calloop.pikabu_demo.data.repositories.SharedPreferences.session;

public interface ISessionPreferences {
    void startUserSession(int accountId);

    int getAccountId();

    boolean sessionStarted();

    void endUserSession();
}
