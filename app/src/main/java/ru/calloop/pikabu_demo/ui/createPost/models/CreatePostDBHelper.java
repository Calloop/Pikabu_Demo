package ru.calloop.pikabu_demo.ui.createPost.models;

import java.util.ArrayList;
import java.util.List;

public class CreatePostDBHelper {
    private final List<PostItem> localPostItemList;
    private List<PostItem> dbPostItemList;

    public CreatePostDBHelper() {
        localPostItemList = new ArrayList<>();
    }

    public List<PostItem> getLocalPostItemList() {
        return localPostItemList;
    }

    public List<PostItem> getDbPostItemList() {
        return dbPostItemList;
    }
}
