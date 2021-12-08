package ru.calloop.pikabu_demo.createPostActivity;

import android.content.ContentValues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.Post;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDbHelper;
import ru.calloop.pikabu_demo.mainActivity.MainContract;
import ru.calloop.pikabu_demo.services.impl.PostItemRepository;
import ru.calloop.pikabu_demo.services.impl.PostRepository;

public class CreatePostPresenter implements CreatePostContract.IPresenter {
    private CreatePostContract.IView view;
    private PostRepository repository;

    public void loadEntry() {
        //postItems..subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ViewModel>() {
        //}
    }

    public CreatePostPresenter() {

    }

    public void attachView(CreatePostContract.IView view) {
        this.view = view;
        PikabuDB database = PikabuDB.getDatabase(view.getContext());
        repository = new PostRepository(database.getPostDao());
    }

    public void detachView() {
        view = null;
    }

    public void loadPostItems() {
        //postItemModel.loadPostItems(items -> view.showPostItems(items));
    }

    @Override
    public long insertPost(Post post) {
        return repository.insertPost(post);
    }

    @Override
    public void insertPostItemList(List<PostItem> postItemList) {
        repository.insertPostItemList(postItemList);
    }

//    @Override
//    public void deleteAll(List<PostItem> postItemList)
//    {
//        repository.deleteAll(postItemList);
//    }

//    @Override
//    public void addEntry(List<PostItem> postItems) {
//        ContentValues cv = new ContentValues(4);
//
//        for (PostItem postItem : postItems) {
//            cv.put(PostItemDbHelper.COLUMN.POST_ID, postItem.getPostId());
//            cv.put(PostItemDbHelper.COLUMN.POSITION, postItem.getDataPosition());
//            cv.put(PostItemDbHelper.COLUMN.TYPE, postItem.getDataType());
//            cv.put(PostItemDbHelper.COLUMN.VALUE, postItem.getDataValue());
//
//            //postItemModel.addEntry(cv, this::loadPostItems);
//        }
//    }
}

//    @Override
//    public void clearEntry() {
//        iView.showProgress();
//        iModel.clearUsers(new PostItemModel().CompleteCallback() {
//            @Override
//            public void onComplete() {
//                iView.hideProgress();
//                loadUsers();
//            }
//        });
//    }