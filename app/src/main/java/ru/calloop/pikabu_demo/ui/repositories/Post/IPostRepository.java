package ru.calloop.pikabu_demo.ui.repositories.Post;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.Post;
import ru.calloop.pikabu_demo.ui.models.PostWithPostItems;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public interface IPostRepository {
    void insert(int accountId, String postHeadline, List<PostItem> postItemList);
    List<PostWithPostItems> getPostItems(int startPosition, int limitCount);
    List<Post> getAllPosts(int startPosition, int limitCount);
    long insertPost(Post post);
    void insertPostItemList(List<PostItem> postItemList);

    List<PostItem> loadCachePost();
}
