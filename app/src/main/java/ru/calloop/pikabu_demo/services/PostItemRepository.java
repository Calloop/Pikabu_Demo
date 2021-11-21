package ru.calloop.pikabu_demo.services;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public interface PostItemRepository {

    List<PostItem> getAll();
    PostItem getById(int id);
//    void insert(PostItem postItem);
//    int delete(PostItem postItem);
}
