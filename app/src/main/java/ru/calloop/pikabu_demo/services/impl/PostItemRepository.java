package ru.calloop.pikabu_demo.services.impl;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDao;
import ru.calloop.pikabu_demo.services.IPostItemRepository;

public class PostItemRepository implements IPostItemRepository {

    Context context;
    PostItemDao postItemDao;

    @Inject
    public PostItemRepository(Context context) {
        this.context = context;

        PikabuDB database = PikabuDB.getDatabase(context);
        postItemDao = database.getPostItemDao();
    }

    @Override
    public Observable<List<PostItem>> getAll() {
        return postItemDao.getAll();
    }

    @Override
    public Observable<PostItem> getById(int id) {
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
