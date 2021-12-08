package ru.calloop.pikabu_demo.createPostActivity.postItem;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostItemDao {
    @Query("SELECT * FROM postItems")
    List<PostItem> getAll();

    @Query("SELECT * FROM postItems WHERE id = :id")
    PostItem getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PostItem> postItemList);

    @Update
    void update(PostItem postItem);

    @Delete
    int delete(PostItem postItem);

    @Delete
    void deleteAll(List<PostItem> postItemList);
}
