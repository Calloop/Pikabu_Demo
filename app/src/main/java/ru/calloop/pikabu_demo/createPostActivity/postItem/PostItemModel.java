package ru.calloop.pikabu_demo.createPostActivity.postItem;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostContract;
import ru.calloop.pikabu_demo.services.PostItemRepository;
import ru.calloop.pikabu_demo.services.impl.PostItemRepositoryImpl;

public class PostItemModel extends AndroidViewModel {
    private PostItemRepository repository;

    private List<PostItem> postItems;

    private PostItemDao postItemDao;

    public PostItemModel(Application application) {
        super(application);
        postItemDao = PikabuDB.getDatabase(application).getPostItemDao();
        //repository = new PostItemRepositoryImpl(application);
        //postItems = repository.getAll();
    }

    public List<PostItem> getAll() {
        return postItemDao.getAll();
    }

//    public interface LoadEntriesCallback {
//        void onLoad(List<PostItem> data);
//    }
//
//    public interface CompleteCallback {
//        void onComplete();
//    }
//
//    public void loadPostItems(PostItemModel.LoadEntriesCallback callback) {
//        SQLiteDatabase db = postItemDbHelper.getReadableDatabase();
//        List<PostItem> postItems = new ArrayList<>();
//        Cursor cursor = db.query(PostItemDbHelper.DATABASE_DATA.TABLE_POSTITEMS, null, null,
//                null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                int postId = cursor.getInt(1);
//                int dataPosition = cursor.getInt(2);
//                int dataType = cursor.getInt(3);
//                String dataValue = cursor.getString(4);
//
//                PostItem postItem = new PostItem(id, postId, dataPosition, dataType, dataValue);
//                postItems.add(postItem);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//
//        callback.onLoad(postItems);
//    }
//
//    public void addEntry(ContentValues contentValues, CompleteCallback callback) {
//        postItemDbHelper.getWritableDatabase()
//                .insert(PostItemDbHelper.DATABASE_DATA.TABLE_POSTITEMS, null,
//                        contentValues);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        callback.onComplete();
//    }
//
//    public void clearEntry(CompleteCallback completeCallback) {
//        postItemDbHelper.getWritableDatabase()
//                .delete(PostItemDbHelper.DATABASE_DATA.TABLE_POSTITEMS,
//                        null, null);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        completeCallback.onComplete();
//    }
}