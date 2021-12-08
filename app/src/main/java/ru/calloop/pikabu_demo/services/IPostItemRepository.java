package ru.calloop.pikabu_demo.services;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public interface IPostItemRepository {

    List<PostAndPostItem> getPostItems(List<Integer> postIdList);
    List<Post> getAllPosts(int startPosition, int limitCount);
    long insertPost(Post post);
    void insertPostItemList(List<PostItem> postItemList);
    //PostItem getById(int id);
    //void insertAll(List<PostItem> postItemList);

    //void deleteAll(List<PostItem> postItemList);
    //void insertItem(PostItem postItem);
//    int delete(PostItem postItem);
}
