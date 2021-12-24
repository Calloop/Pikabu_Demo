package ru.calloop.pikabu_demo.createPostActivity.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPost(Post post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPostItemList(List<PostItem> postItemList);

    @Transaction
    @Query("SELECT * FROM posts LIMIT :startPosition, :limitCount")
    List<PostAndPostItem> getPostItems(int startPosition, int limitCount);

    @Query("SELECT * FROM posts LIMIT :startPosition, :limitCount")
    List<Post> getAllPosts(int startPosition, int limitCount);

    @Query("SELECT id FROM posts LIMIT :startPosition, :limitCount")
    Integer getPostsId(int startPosition, int limitCount);




//    @Transaction
//    @Query("SELECT * FROM posts WHERE id IN (SELECT * FROM posts LIMIT :startPosition, :limitCount)")
//    List<PostAndPostItem> getPostItems(int startPosition, int limitCount);

    @Update
    void update(Post post);

    @Delete
    int delete(Post post);

    @Delete
    void deleteAll(List<Post> postList);
}
