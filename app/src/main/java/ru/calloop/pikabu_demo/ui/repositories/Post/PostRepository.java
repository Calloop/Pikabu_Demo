package ru.calloop.pikabu_demo.ui.repositories.Post;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.models.Post;
import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.models.PostWithPostItems;

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
    public List<PostWithPostItems> getPostItems(int startPosition, int limitCount) {
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
    public void insert(int accountId, String postHeadline, @NonNull List<PostItem> postItems) {
        Post post = new Post(accountId, postHeadline);
        int postId = (int) insertPost(post);
        IntStream.range(0, postItems.size()).forEach(i -> {
            postItems.get(i).setPosition(i + 1);
            postItems.get(i).setPostId(postId);
        });
        insertPostItemList(postItems);
    }

    @Override
    public List<PostItem> loadCachePost() {
        return null;
    }
}