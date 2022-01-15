package ru.calloop.pikabu_demo.createPostActivity;

import android.content.Context;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.models.Post;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;
import ru.calloop.pikabu_demo.signingActivity.models.Account;

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

        void insert(Post post, List<PostItem> postItemList);

        void insertPostItemList(List<PostItem> postItemList);

//        void deleteAll(List<PostItem> postItemList);

        void loadPostItems();
        //void addEntry(List<PostItem> postItems);
        //void clearEntry();
    }
}
