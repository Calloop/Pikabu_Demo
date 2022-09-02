package ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostItem;

public class createPostPreferences implements ICreatePostPreferences {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final Editor editor;
    private final Gson gson;

    private final String KEY_POST_PREFERENCE = "postPreference";
    private final String KEY_POST_HEADLINE = "postHeadline";
    private final String KEY_POST_ITEMS = "postItems";

    public createPostPreferences(Context context) {
        this.context = context;
        sharedPreferences = this.context.
                getSharedPreferences(KEY_POST_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    @Override
    public String getPostHeadline() {
        return sharedPreferences.getString(KEY_POST_HEADLINE, null);
    }

    @Override
    public List<PostItem> getPostItems() {
        String json = sharedPreferences.getString(KEY_POST_ITEMS, null);
        Log.d("TAG", "getPostItems: JSON NOT NULL");
        if (json != null) {
            Type type = new TypeToken<List<PostItem>>() {
            }.getType();
            return gson.fromJson(json, type);
        } else return new ArrayList<>();
    }

    @Override
    public void setPostHeadline(String postHeadline) {
        editor.putString(KEY_POST_HEADLINE, postHeadline);
        editor.apply();
    }

    @Override
    public void setPostItems(List<PostItem> postItems) {
        String json = gson.toJson(postItems);
        editor.putString(KEY_POST_ITEMS, json);
        editor.apply();
    }

    @Override
    public void clearPreference() {
        Log.d("TAG", "clearPreference: ");
        editor.clear().apply();
    }
}
