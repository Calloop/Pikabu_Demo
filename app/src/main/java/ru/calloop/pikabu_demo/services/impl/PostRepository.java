package ru.calloop.pikabu_demo.services.impl;

import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostDao;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.services.IPostRepository;

public class PostRepository implements IPostRepository {
    PostDao postDao;

    @Inject
    public PostRepository(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<PostAndPostItem> getPostItems(List<Integer> postIdList) {
        return postDao.getPostItems(postIdList);
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
}