package ru.calloop.pikabu_demo.createPostPage;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.createPostPage.models.ListItemCreatePost;

public interface CreatePostContract {
    interface View {
        void listIsEmpty();
    }

    interface Presenter {
//        void buttonAddTextCreatePostClicked();
//        void buttonAddImageCreatePostClicked();
//        void buttonDeleteBlockCreatePostClicked();
        void loadData();
        void onDestroy();
    }

    interface Model {
        void getData();
    }
}
