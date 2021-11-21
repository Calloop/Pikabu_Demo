package ru.calloop.pikabu_demo.createPostActivity;

import android.content.ContentValues;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;

public interface CreatePostContract {
    interface IModel {
        //void loadPostItems(PostItemModel.LoadEntriesCallback callback);
        //void addEntry(ContentValues contentValues, PostItemModel.CompleteCallback callback);
        //void clearEntry(PostItemModel.CompleteCallback completeCallback);
    }

    interface IView {
        void showPostItems(List<PostItem> postItems);
    }

    interface IPresenter {
        void attachView(IView view);
        void detachView();

        void loadPostItems();
        void addEntry(List<PostItem> postItems);
        //void clearEntry();
    }
}
