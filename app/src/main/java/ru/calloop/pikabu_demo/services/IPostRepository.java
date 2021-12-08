package ru.calloop.pikabu_demo.services;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public interface IPostRepository {
    List<PostAndPostItem> getPostItems(List<Integer> postIdList);
    List<Post> getAllPosts(int startPosition, int limitCount);
    long insertPost(Post post);
    void insertPostItemList(List<PostItem> postItemList);
}
