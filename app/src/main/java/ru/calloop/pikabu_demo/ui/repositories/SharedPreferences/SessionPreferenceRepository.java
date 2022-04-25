package ru.calloop.pikabu_demo.ui.repositories.SharedPreferences;

import static android.content.SharedPreferences.Editor;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreferenceRepository implements ISessionPreferenceRepository {
    public static final String KEY = "SESSION_PREFERENCES";
    public static final String ID = "ID";
    public static final String AUTHORIZED = "AUTHORIZED";

    private final SharedPreferences sharedPreferences;
    private final Editor editor;
    private final Context context;

    public SessionPreferenceRepository(Context context) {
        this.context = context;
        sharedPreferences = context
                .getSharedPreferences(KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void startUserSession(int accountId) {
        editor.putBoolean(AUTHORIZED, true);
        editor.putInt(ID, accountId);
//            editor.putString("LOGIN", login);
//            editor.putString("EMAIL", email);
        editor.commit();
    }

    @Override
    public long getAccountId() {
        return sharedPreferences.getLong(ID, 0);
    }

    @Override
    public boolean sessionStarted() {
        return sharedPreferences.getBoolean(AUTHORIZED, false);
    }

    @Override
    public void endUserSession() {
        editor.clear();
        editor.apply();
    }
}
