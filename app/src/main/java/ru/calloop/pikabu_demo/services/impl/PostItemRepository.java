package ru.calloop.pikabu_demo.services.impl;

import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostDao;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.services.IPostItemRepository;

public class PostItemRepository implements IPostItemRepository {
    PostDao postDao;

    @Inject
    public PostItemRepository(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<PostAndPostItem> getPostItems(List<Integer> postIdList) {
        return postDao.getPostItems(postIdList);
    }

    @Override
    public List<Post> getAllPosts(int startPosition, int limitCount)
    {
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

//    @Override
//    public PostItem getById(int id) {
//        return postItemDao.getById(id);
//    }
//
//    @Override
//    public void insertAll(List<PostItem> postItemList) {
//        postItemDao.insertAll(postItemList);
//    }
//
//    @Override
//    public void deleteAll(List<PostItem> postItemList)
//    {
//        postItemDao.deleteAll(postItemList);
//    }

//    @Override
//    public void insertItem(PostItem postItem) {
//        postItemDao.insertItem(postItem);
//    }
//
//    @Override
//    public int delete(PostItem postItem) {
//        return PostItemDao.delete(postItem);
//    }
}
