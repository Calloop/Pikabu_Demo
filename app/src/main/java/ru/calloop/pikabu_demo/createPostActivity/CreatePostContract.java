package ru.calloop.pikabu_demo.createPostActivity;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
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
        Context getContext();
    }

    interface IPresenter {
        void attachView(IView view);

        void detachView();

        long insertPost(Post post);

        void insertPostItemList(List<PostItem> postItemList);

//        void deleteAll(List<PostItem> postItemList);

        void loadPostItems();
        //void addEntry(List<PostItem> postItems);
        //void clearEntry();
    }
}
