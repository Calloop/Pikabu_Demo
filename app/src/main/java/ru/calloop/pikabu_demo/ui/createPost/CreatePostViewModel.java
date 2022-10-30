package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost.ICreatePostPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost.createPostPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.SessionPreferences;

public class CreatePostViewModel extends AndroidViewModel {

    private final SavedStateHandle state;
    private final IPostRepository postRepository;
    private final ICreatePostPreferences createPostPreferences;
    private final ISessionPreferences sessionPreferences;
    private MutableLiveData<Integer> newPostItemType;
    private MutableLiveData<Integer> newPostItemPosition;
    private MutableLiveData<int[]> newPostItemData;

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

    public LiveData<int[]> getNewPostItemData() {
        if (newPostItemData == null) {
            newPostItemData = new MutableLiveData<>();
        }
        return newPostItemData;
    }

    public void setNewPostItemData(int newPostItemType, int newPostItemPosition) {
        this.newPostItemData.postValue(new int[]{newPostItemType, newPostItemPosition});
    }

    //region [NEW POST_ITEM TYPE]
    public LiveData<Integer> getNewPostItemType() {
        if (newPostItemType == null) {
            newPostItemType = new MutableLiveData<>();
        }
        return newPostItemType;
    }

    public void setNewPostItemType(int newPostItemType) {
        this.newPostItemType.postValue(newPostItemType);
    }

    public LiveData<Integer> getNewPostItemPosition() {
        if (newPostItemPosition == null) {
            newPostItemPosition = new MutableLiveData<>();
        }
        return newPostItemPosition;
    }

    public void setNewPostItemPosition(int newPostItemPosition) {
        this.newPostItemPosition.postValue(newPostItemPosition);
    }
    //endregion

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

    public boolean getIsReadPermissionGranted() {
        return Boolean.TRUE.equals(state.get("read"));
    }

    public void setIsReadPermissionGranted(boolean isReadPermissionGranted) {
        state.set("read", isReadPermissionGranted);
    }

    public boolean getIsWritePermissionGranted() {
        return Boolean.TRUE.equals(state.get("write"));
    }

    public void setIsWritePermissionGranted(boolean isWritePermissionGranted) {
        state.set("write", isWritePermissionGranted);
    }

    //region [OPERATIONS]
    public boolean upToDateMediaLinks(int postItemsType, @NonNull List<PostItem> postItems) {
        int lostMedia = 0;

        for (PostItem postItem : postItems) {
            if (postItem.getType() == 3) {
                try {
                    File file = new File(postItem.getValue());
                    if (!file.exists()) {
                        postItems.remove(postItem);
                        lostMedia++;
                    }
                } catch (Exception e) {

                }

//                File file = new File(postItem.getValue());
//                if (!file.exists()) {
//                    i.remove();
//                    lostMedia++;
//                }
            }
        }

        if (postItemsType == 1) {
            setPostItems(postItems);
        } else if (postItemsType == 2) {
            setPostItemsFromSavedState(postItems);
        }

        return lostMedia != 0;
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

    //region [IMAGE OPERATIONS]
    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "http://cdn.meme.am/instances/60677654.jpg";
    }

    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_create_post_add_post)
                .into(view);
    }
    //endregion
}