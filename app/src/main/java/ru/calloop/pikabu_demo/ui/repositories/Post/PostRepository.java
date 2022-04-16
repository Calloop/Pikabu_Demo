package ru.calloop.pikabu_demo.ui.repositories.Post;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.ui.createPost.models.Post;
import ru.calloop.pikabu_demo.ui.createPost.models.PostAndPostItem;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;

public class PostRepository implements IPostRepository {
    private final IPostDao postDao;

    @Inject
    public PostRepository(IPostDao postDao) {
        this.postDao = postDao;
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
    public void insertPostItemList(List<PostItem> postItemList) {
        postDao.insertPostItemList(postItemList);
    }

    @Override
    public void insert(Post post, List<PostItem> postItemList) {

        long postId = insertPost(post);

        for (PostItem postItem : postItemList) {
            postItem.setPostId(postId);
        }

        insertPostItemList(postItemList);
    }
}