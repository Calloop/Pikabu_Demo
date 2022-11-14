package ru.calloop.pikabu_demo.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;

import ru.calloop.pikabu_demo.data.models.PostItem;
import ru.calloop.pikabu_demo.data.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.data.repositories.Post.PostRepository;
import ru.calloop.pikabu_demo.data.repositories.SharedPreferences.createPost.ICreatePostPreferences;
import ru.calloop.pikabu_demo.data.repositories.SharedPreferences.createPost.createPostPreferences;
import ru.calloop.pikabu_demo.data.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.data.repositories.SharedPreferences.session.SessionPreferences;

public class CreatePostViewModel extends AndroidViewModel {

    private final SavedStateHandle state;
    private final IPostRepository postRepository;
    private final ICreatePostPreferences createPostPreferences;
    private final ISessionPreferences sessionPreferences;
    private MutableLiveData<PostItem> newPostItem;

    // ПЕРЕМЕСТИТЬ В ОБЩИК СПИСОК ПЕРЕМЕННЫХ
    public static final String ACTION_MODE_STATE_KEY = "actionModeState";
    public static final String POST_ITEMS_SAVED_STATE_KEY = "postItemsFromSavedState";
    public static final int POST_ITEMS_FROM_PREFERENCES = 1;
    public static final int POST_ITEMS_FROM_SAVED_STATE = 2;

    public CreatePostViewModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application);
        state = stateHandle;
        postRepository = new PostRepository(application);
        createPostPreferences = new createPostPreferences(application);
        sessionPreferences = new SessionPreferences(application);
    }

    public void insertPostToDB(int accountId, String postHeadline, List<PostItem> postItemList) {
        postRepository.insert(accountId, postHeadline, postItemList);
    }

    public int getAccountId() {
        return sessionPreferences.getAccountId();
    }

    public LiveData<PostItem> getNewPostItem() {
        if (newPostItem == null) {
            newPostItem = new MutableLiveData<>();
        }
        return newPostItem;
    }

    public void setNewPostItem(PostItem postItem) {
        newPostItem.postValue(postItem);
    }

    //region [ACTION MODE]
    public boolean getActionModeState() {
        return Boolean.TRUE.equals(state.get(ACTION_MODE_STATE_KEY));
    }

    public void setActionModeState(boolean actionModeState) {
        state.set(ACTION_MODE_STATE_KEY, actionModeState);
    }
    //endregion

    //region [POST ITEMS SAVED STATE OPERATIONS]
    public List<PostItem> getPostItemsFromSavedState() {
        return state.get(POST_ITEMS_SAVED_STATE_KEY);
    }

    public void setPostItemsFromSavedState(List<PostItem> postItems) {
        state.set(POST_ITEMS_SAVED_STATE_KEY, postItems);
    }
    //endregion

    //region [POST OPERATIONS]
    public String getPostHeadline() {
        return createPostPreferences.getPostHeadline();
    }

    public void setPostHeadline(String postHeadline) {
        createPostPreferences.setPostHeadline(postHeadline);
    }

    public List<PostItem> getPostItems() {
        return createPostPreferences.getPostItems();
    }

    public void setPostItems(List<PostItem> postItems) {
        createPostPreferences.setPostItems(postItems);
    }

    public void clearPreferences() {
        createPostPreferences.clearPreference();
    }
    //endregion
}