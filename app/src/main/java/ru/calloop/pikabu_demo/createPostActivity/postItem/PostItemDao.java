package ru.calloop.pikabu_demo.createPostActivity.postItem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface PostItemDao {
    @Query("SELECT * FROM postItems")
    List<PostItem> getAll();

    @Query("SELECT * FROM postItems WHERE id = :id")
    PostItem getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostItem postItem);

    @Update
    void update(PostItem postItem);

    @Delete
    int delete(PostItem postItem);
}
