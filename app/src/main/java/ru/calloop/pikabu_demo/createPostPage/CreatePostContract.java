package ru.calloop.pikabu_demo.createPostPage;

import java.util.List;

import ru.calloop.pikabu_demo.createPostPage.Model.PostData;

public interface CreatePostContract {
    interface IView {
        void setDataToListview(List<PostData> list);
    }

    interface IPresenter {
        void setDataToListview();
    }

    interface IModel {
        List<PostData> getListFromDatabase();
    }
}
