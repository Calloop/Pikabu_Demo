package ru.calloop.pikabu_demo.ui.createPost.adapters.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Iterator;
import java.util.Map;

import ru.calloop.pikabu_demo.ui.createPost.fragments.CreatePostFragment;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class CustomContract extends ActivityResultContract<Object[], Object[]> {

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, @NonNull Object[] objects) {
        Intent intent = (Intent) objects[0];
        PostItem postItem = (PostItem) objects[1];
        intent.putExtra("parameters", postItem);
        return intent;
    }

    @Override
    public Object[] parseResult(int resultCode, @Nullable Intent intent) {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            Object[] result = new Object[2];
            result[0] = intent;
            result[1] = intent.getSerializableExtra("parameters");
            return result;
        }
        return null;
    }
}