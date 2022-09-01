package ru.calloop.pikabu_demo.ui.main.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostWithPostItems;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class HomeViewModel extends AndroidViewModel {
    private final IPostRepository postRepository;
    private final MutableLiveData<List<PostWithPostItems>> posts;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        posts = new MutableLiveData<>();
        posts.postValue(postRepository.getPostItems(0, 100));
    }

    public LiveData<List<PostWithPostItems>> getPosts() {
        return posts;
    }
}