package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;
import java.util.Objects;

import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private final IPostRepository postRepository;
    private final MutableLiveData<List<PostItem>> postItems;
    private final SavedStateHandle stateHandle;

    public CreatePostViewModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application);
        this.stateHandle = stateHandle;
        postRepository = new PostRepository(application);
        postItems = new MutableLiveData<>();
    }

    public LiveData<List<PostItem>> getPostItems() {
        return postItems;
    }

    public void setPostItems(List<PostItem> postItems) {
        this.postItems.postValue(postItems);
    }

    public void setStateHandle(String key, String value) {
        stateHandle.set(key, value);
    }

    public void insertPostToDB(int accountId, String postHeadline) {
        postRepository.insert(accountId, postHeadline, postItems.getValue());
    }
}
