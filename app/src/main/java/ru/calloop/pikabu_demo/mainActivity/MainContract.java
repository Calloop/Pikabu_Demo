package ru.calloop.pikabu_demo.mainActivity;

import android.content.ContentValues;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.CreatePostContract;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;

public interface MainContract {
    interface IModel {
        //void loadPostItems(PostItemModel.LoadEntriesCallback callback);
    }

    interface IView {
        //void showPostItems(List<PostItem> postItems);
    }

    interface IPresenter {
        void attachView(MainContract.IView view);
        void detachView();
        void viewIsReady();

        //void loadPostItems();
    }
}
