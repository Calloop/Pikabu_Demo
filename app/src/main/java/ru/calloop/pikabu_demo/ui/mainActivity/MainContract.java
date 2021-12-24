package ru.calloop.pikabu_demo.ui.mainActivity;

import android.content.Context;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.models.Post;
import ru.calloop.pikabu_demo.createPostActivity.models.PostAndPostItem;

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
        List<PostAndPostItem> getPostItems(int startPosition, int limitCount);
        List<Post> getAllPosts(int startPosition, int limitCount);
        void attachView(MainContract.IView view);
        void detachView();
        void viewIsReady();

        //void loadPostItems();
    }
}
