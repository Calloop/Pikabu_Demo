package ru.calloop.pikabu_demo.ui.repositories.SharedPreferences;

public interface ISessionPreferenceRepository {
    void startUserSession(int accountId);

    int getAccountId();

    boolean sessionStarted();

    void endUserSession();
}
