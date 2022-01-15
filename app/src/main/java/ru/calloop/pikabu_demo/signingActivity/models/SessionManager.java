package ru.calloop.pikabu_demo.signingActivity.models;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String KEY = "SESSION_PREFERENCES";
    public static final String ID = "ID";
    public static final String AUTHORIZED = "AUTHORIZED";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context
                .getSharedPreferences(KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void startUserSession(int accountId) {
        editor.putBoolean(AUTHORIZED, true);
        editor.putInt(ID, accountId);
//            editor.putString("LOGIN", login);
//            editor.putString("EMAIL", email);
        editor.commit();
    }

    public boolean isSessionActive() {
        return sharedPreferences.getBoolean(AUTHORIZED, true);
    }

    public void endUserSession() {
        editor.clear();
        editor.apply();
    }
}
