package ru.calloop.pikabu_demo.createPostPage.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.calloop.pikabu_demo.createPostPage.PostDataTable;
import ru.calloop.pikabu_demo.createPostPage.View.DbHelper;

public class PostDataModel {
    private final DbHelper dbHelper;

    public PostDataModel(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void loadData(LoadDataCallback callback) {
        LoadDataTask loadUsersTask = new LoadDataTask(callback);
        loadUsersTask.execute();
    }

    public void addData(ContentValues contentValues, CompleteCallback callback) {
        AddDataTask addUserTask = new AddDataTask(callback);
        addUserTask.execute(contentValues);
    }

    public interface LoadDataCallback {
        void onLoad(List<PostData> data);
    }

    interface CompleteCallback {
        void onComplete();
    }

    class LoadDataTask extends AsyncTask<Void, Void, List<PostData>> {

        private final LoadDataCallback callback;

        LoadDataTask(LoadDataCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<PostData> doInBackground(Void... params) {
            List<PostData> data = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query(PostDataTable.TABLE, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    int postId = cursor.getInt(1);
                    int dataPosition = cursor.getInt(2);
                    int dataType = cursor.getInt(3);
                    String dataValue = cursor.getString(4);

                    PostData item = new PostData(id, postId, dataPosition, dataType, dataValue);
                    data.add(item);
                } while (cursor.moveToNext());
            }

            cursor.close();
            return data;
        }

        @Override
        protected void onPostExecute(List<PostData> data) {
            if (callback != null) {
                callback.onLoad(data);
            }
        }
    }

    class AddDataTask extends AsyncTask<ContentValues, Void, Void> {

        private final CompleteCallback callback;

        AddDataTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... params) {
            ContentValues contentValues = params[0];
            dbHelper.getWritableDatabase().insert(DbHelper.TABLE_POSTDATA, null, contentValues);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }
}