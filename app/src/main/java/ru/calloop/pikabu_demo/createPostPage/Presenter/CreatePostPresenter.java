package ru.calloop.pikabu_demo.createPostPage.Presenter;

import java.util.List;

import ru.calloop.pikabu_demo.createPostPage.CreatePostContract;
import ru.calloop.pikabu_demo.createPostPage.Model.ListItemCreatePostModel;
import ru.calloop.pikabu_demo.createPostPage.Model.PostData;

public class CreatePostPresenter implements CreatePostContract.IPresenter {
    private final CreatePostContract.IView iView;
    private final CreatePostContract.IModel iModel;

    public CreatePostPresenter(CreatePostContract.IView iView) {
        this.iView = iView;
        iModel = new ListItemCreatePostModel(iView);
    }

    @Override
    public void setDataToListview() {
        List<PostData> list = iModel.getListFromDatabase();
        iView.setDataToListview(list);
    }
}