package ru.calloop.pikabu_demo.createPostPage;

import android.content.Context;

import java.util.List;

import ru.calloop.pikabu_demo.createPostPage.models.ListItemCreatePost;

public class CreatePostPresenter implements CreatePostContract.Presenter {
    private CreatePostContract.View view;
    private final CreatePostContract.Model model;
    public List<ListItemCreatePost> list;

    public CreatePostPresenter(CreatePostContract.Model model) {
        this.model = model;
    }

    public void attachView(Context context) {
        view = (CreatePostContract.View) context;
    }

//    @Override
//    public void buttonAddTextCreatePostClicked() {
//        view.listIsEmpty(model.buttonAddTextCreatePostClicked());
//    }
//
//    @Override
//    public void buttonAddImageCreatePostClicked() {
//        view.listIsEmpty(model.buttonAddTextCreatePostClicked());
//    }
//
//    public void buttonDeleteBlockCreatePostClicked() {
//        if (list.size() > 0) {
//            for (int i = 0; i < 2; i++) {
//                list.remove(list.size() - 1);
//            }
//        }
//        view.listIsEmpty(list.size());
//    }

    public void loadData() {
        model.getData();
    }

    @Override
    public void onDestroy() {

    }
}
