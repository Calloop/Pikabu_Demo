package ru.calloop.pikabu_demo.mainActivity;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.CreatePostContract;
import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostAndPostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;

public interface MainContract {
    interface IModel {
        //void loadPostItems(PostItemModel.LoadEntriesCallback callback);
    }

    interface IView {
        //void showPostItems(List<PostItem> postItems);
        Context getContext();
    }

    interface IPresenter {
        //void deleteAll(List<PostItem> postItemList);
        List<PostAndPostItem> getPostItems(List<Integer> postIdList);
        List<Post> getAllPosts(int startPosition, int limitCount);
        void attachView(MainContract.IView view);
        void detachView();
        void viewIsReady();

        //void loadPostItems();
    }
}
