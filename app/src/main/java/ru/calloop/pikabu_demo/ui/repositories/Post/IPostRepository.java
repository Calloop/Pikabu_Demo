package ru.calloop.pikabu_demo.ui.repositories.Post;

import java.util.List;

import ru.calloop.pikabu_demo.ui.createPost.models.Post;
import ru.calloop.pikabu_demo.ui.createPost.models.PostAndPostItem;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;

public interface IPostRepository {
    List<PostAndPostItem> getPostItems(int startPosition, int limitCount);
    List<Post> getAllPosts(int startPosition, int limitCount);
    long insertPost(Post post);
    void insertPostItemList(List<PostItem> postItemList);
    void insert(Post post, List<PostItem> postItemList);
}
