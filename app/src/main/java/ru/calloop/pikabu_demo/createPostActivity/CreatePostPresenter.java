package ru.calloop.pikabu_demo.createPostActivity;

import android.content.ContentValues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDbHelper;

public class CreatePostPresenter implements CreatePostContract.IPresenter {

    List<PostItem> postItems;

//    LiveData<List<PostItem>> getAll() {
//        return postItems;
//    }

    public void loadEntry() {
        //postItems..subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ViewModel>() {
        //}
        }

    private CreatePostContract.IView view;
    private final PostItemModel postItemModel;

    public CreatePostPresenter(PostItemModel postItemModel) {
        this.postItemModel = postItemModel;
    }

    public void attachView(CreatePostContract.IView view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public void loadPostItems() {
        //postItemModel.loadPostItems(items -> view.showPostItems(items));
    }

    @Override
    public void addEntry(List<PostItem> postItems) {
        ContentValues cv = new ContentValues(4);

        for (PostItem postItem1 : postItems) {
            cv.put(PostItemDbHelper.COLUMN.POST_ID, postItem1.getPostId());
            cv.put(PostItemDbHelper.COLUMN.POSITION, postItem1.getDataPosition());
            cv.put(PostItemDbHelper.COLUMN.TYPE, postItem1.getDataType());
            cv.put(PostItemDbHelper.COLUMN.VALUE, postItem1.getDataValue());

            //postItemModel.addEntry(cv, this::loadPostItems);
        }
    }
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