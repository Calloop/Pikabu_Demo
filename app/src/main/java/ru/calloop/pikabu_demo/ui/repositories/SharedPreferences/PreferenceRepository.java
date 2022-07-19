package ru.calloop.pikabu_demo.ui.repositories.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostItem;

public class PreferenceRepository implements IPreferenceRepository {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final Editor editor;
    private final Gson gson;

    public PreferenceRepository(Context context) {
        this.context = context;
        sharedPreferences = this.context.
                getSharedPreferences("POST_PREFERENCE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    @Override
    public String getPostHealdine() {
        String json = sharedPreferences.getString("POST_HEADLINE", null);

        if (json != null) {
            return json;
        } else return "";
    }

    @Override
    public List<PostItem> getPostItems() {
        String json = sharedPreferences.getString("POST_ITEMS", null);

        if (json != null) {
            Type type = new TypeToken<List<PostItem>>() {
            }.getType();
            return gson.fromJson(json, type);
        } else return new ArrayList<>();
    }

    @Override
    public void setPostHeadline(String postHeadline) {
        editor.putString("POST_HEADLINE", postHeadline);
        editor.apply();
    }

    @Override
    public void setPostItems(List<PostItem> postItems) {
        String json = gson.toJson(postItems);
        editor.putString("POST_ITEMS", json);
        editor.apply();
    }

    @Override
    public void clearPreference() {
        editor.clear();
        editor.apply();
    }
}
