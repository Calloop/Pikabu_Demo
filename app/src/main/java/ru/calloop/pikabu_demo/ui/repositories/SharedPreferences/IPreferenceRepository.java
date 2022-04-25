package ru.calloop.pikabu_demo.ui.repositories.SharedPreferences;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostItem;

public interface IPreferenceRepository {
    String getPostHealdine();

    List<PostItem> getPostItems();

    void setPostHeadline(String postHeadline);

    void setPostItems(List<PostItem> postItems);

    void clearPreference();
}
