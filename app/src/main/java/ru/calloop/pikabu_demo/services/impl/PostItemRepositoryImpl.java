package ru.calloop.pikabu_demo.services.impl;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDao;
import ru.calloop.pikabu_demo.services.PostItemRepository;

public class PostItemRepositoryImpl implements PostItemRepository {

    Context context;
    PostItemDao postItemDao;

    @Inject
    public PostItemRepositoryImpl(Context context) {
        this.context = context;

        PikabuDB database = PikabuDB.getDatabase(context);
        postItemDao = database.getPostItemDao();
    }

    @Override
    public List<PostItem> getAll() {
        return postItemDao.getAll();
    }

    @Override
    public PostItem getById(int id) {
        return postItemDao.getById(id);
    }

//    @Override
//    public void insert(PostItem postItem) {
//        return PostItemDao.insert(postItem);
//    }
//
//    @Override
//    public int delete(PostItem postItem) {
//        return PostItemDao.delete(postItem);
//    }
}
