package ru.calloop.pikabu_demo.data.repositories.SharedPreferences.createPost;

import java.util.List;

import ru.calloop.pikabu_demo.data.models.PostItem;

public interface ICreatePostPreferences {
    String getPostHeadline();

    List<PostItem> getPostItems();

    void setPostHeadline(String postHeadline);

    void setPostItems(List<PostItem> postItems);

    void clearPreference();
}
