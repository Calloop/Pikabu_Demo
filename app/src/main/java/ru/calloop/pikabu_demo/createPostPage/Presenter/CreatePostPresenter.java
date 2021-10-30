package ru.calloop.pikabu_demo.createPostPage.Presenter;

import ru.calloop.pikabu_demo.createPostPage.Model.PostDataModel;
import ru.calloop.pikabu_demo.createPostPage.View.CreatePostActivity;

public class CreatePostPresenter {
    private CreatePostActivity view;
    private PostDataModel postDataModel;

    public CreatePostPresenter(PostDataModel postDataModel) {
        this.postDataModel = postDataModel;
    }

    public void attachView(CreatePostActivity createPostActivity) {
        view = createPostActivity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadData();
    }

    public void loadData() {
        postDataModel.loadData(items -> view.showData(items));
    }

    public void add() {
//        PostData postData = iView.getData;
//        if (TextUtils.isEmpty(postData.getType()) || TextUtils.isEmpty(postData.getText())) {
//            iView.showToast(R.string.empty_values);
//            return;
//        }
//
//        ContentValues cv = new ContentValues(2);
//        cv.put(DbHelper., postData.getType());
//        cv.put(UserTable.COLUMN.EMAIL, postData.getText());
//        iView.showProgress();
//        iModel.addUser(cv, new PostDataModel().CompleteCallback() {
//            @Override
//            public void onComplete() {
//                iView.hideProgress();
//                loadUsers();
//            }
//        });
    }

    public void clear() {
//        iView.showProgress();
//        iModel.clearUsers(new PostDataModel().CompleteCallback() {
//            @Override
//            public void onComplete() {
//                iView.hideProgress();
//                loadUsers();
//            }
//        });
    }
}