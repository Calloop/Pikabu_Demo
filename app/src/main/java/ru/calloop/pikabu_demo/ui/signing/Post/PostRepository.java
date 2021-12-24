package ru.calloop.pikabu_demo.ui.signing.Post;

import java.util.List;

import javax.inject.Inject;

import ru.calloop.pikabu_demo.createPostActivity.models.Post;
import ru.calloop.pikabu_demo.createPostActivity.models.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.models.PostDao;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;

public class PostRepository implements IPostRepository {
    PostDao postDao;

    @Inject
    public PostRepository(PostDao postDao) {
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