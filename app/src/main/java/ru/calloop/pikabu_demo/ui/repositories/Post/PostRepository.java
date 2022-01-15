package ru.calloop.pikabu_demo.ui.repositories.Post;

import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.createPostActivity.models.Post;
import ru.calloop.pikabu_demo.createPostActivity.models.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;

public class PostRepository implements IPostRepository {
    IPostDao IPostDao;

    @Inject
    public PostRepository(IPostDao IPostDao) {
        this.IPostDao = IPostDao;
    }

    @Override
    public List<PostAndPostItem> getPostItems(int startPosition, int limitCount) {
        return IPostDao.getPostItems(startPosition, limitCount);
    }

    @Override
    public List<Post> getAllPosts(int startPosition, int limitCount) {
        return IPostDao.getAllPosts(0, 5);
    }

    @Override
    public long insertPost(Post post) {
        return IPostDao.insertPost(post);
    }

    @Override
    public void insertPostItemList(List<PostItem> postItemList) {
        IPostDao.insertPostItemList(postItemList);
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