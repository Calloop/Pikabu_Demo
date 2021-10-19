package ru.calloop.pikabu_demo.createPostPage.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.createPostPage.CreatePostContract;
import ru.calloop.pikabu_demo.createPostPage.View.DbHelper;

public class ListItemCreatePostModel implements CreatePostContract.IModel {
    private final SQLiteDatabase database;

    public ListItemCreatePostModel(CreatePostContract.IView iView) {
        database = new DbHelper((Context) iView).getWritableDatabase();
    }

    @Override
    public List<PostData> getListFromDatabase() {
        List<PostData> list = new ArrayList<>();
        String sqlQueryText = "SELECT adam, til FROM makal";
        Cursor cursor = this.database.rawQuery(sqlQueryText, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return list;
    }

}