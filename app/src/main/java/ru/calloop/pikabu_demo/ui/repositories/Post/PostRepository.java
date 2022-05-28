package ru.calloop.pikabu_demo.ui.repositories.Post;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.models.Post;
import ru.calloop.pikabu_demo.ui.models.PostAndPostItem;
import ru.calloop.pikabu_demo.ui.models.PostItem;

public class PostRepository implements IPostRepository {
    private final IPostDao postDao;

    @Inject
    public PostRepository(Context context) {
        PikabuDB database = PikabuDB.getDatabase(context);
        postDao = database.getPostDao();
    }

    @Override
    public void insertPostItemList(List<PostItem> postItemList) {
        postDao.insertPostItemList(postItemList);
    }

    @Override
    public List<PostAndPostItem> getPostItems(int startPosition, int limitCount) {
        return postDao.getPostItems(startPosition, limitCount);
    }

    @Override
    public List<Post> getAllPosts(int startPosition, int limitCount) {
        return postDao.getAllPosts(0, 5);
    }

    @Override
    public long insertPost(Post post) {
        return postDao.insertPost(post);
    }

    @Override
    public void insert(int accountId, String postHeadline, List<PostItem> postItemList) {
        Log.d("TEST", "ins");
        Post post = new Post(accountId, postHeadline);

        int postId = (int) insertPost(post);

        for (PostItem postItem : postItemList) {
            postItem.setPostId(postId);
        }

        insertPostItemList(postItemList);
    }

    @Override
    public List<PostItem> loadCachePost() {
        return null;
    }
}