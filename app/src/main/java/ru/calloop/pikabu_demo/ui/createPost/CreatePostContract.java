package ru.calloop.pikabu_demo.ui.createPost;

import android.content.Context;

import java.util.List;

import ru.calloop.pikabu_demo.ui.createPost.models.Post;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;

public interface CreatePostContract {
    interface IModel {
        //void loadPostItems(PostItemModel.LoadEntriesCallback callback);
        //void addEntry(ContentValues contentValues, PostItemModel.CompleteCallback callback);
        //void clearEntry(PostItemModel.CompleteCallback completeCallback);
    }

    interface IView {
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
