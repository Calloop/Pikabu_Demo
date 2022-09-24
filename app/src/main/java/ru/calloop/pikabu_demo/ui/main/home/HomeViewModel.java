package ru.calloop.pikabu_demo.ui.main.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostWithPostItems;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class HomeViewModel extends AndroidViewModel {
    private final SavedStateHandle state;
    private final IPostRepository postRepository;
    private final MutableLiveData<List<PostWithPostItems>> posts;

    private static final String NAME_KEY = "name";

    public HomeViewModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application);
        state = stateHandle;
        postRepository = new PostRepository(application);
        posts = new MutableLiveData<>();
    }

    private void loadPosts() {
        posts.postValue(postRepository.getPostItems(0, 100));
    }

    public LiveData<List<PostWithPostItems>> getPosts() {
        if (state.keys().isEmpty()) {
            loadPosts();
            return posts;
        } else {
            return state.getLiveData(NAME_KEY);
        }
    }

    public void setCachedPosts(List<PostWithPostItems> postWithPostItems) {
        state.set(NAME_KEY, postWithPostItems);
    }
}