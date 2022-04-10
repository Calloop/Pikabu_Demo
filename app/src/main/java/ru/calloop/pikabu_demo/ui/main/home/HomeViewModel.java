package ru.calloop.pikabu_demo.ui.main.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.createPost.models.Post;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class HomeViewModel extends AndroidViewModel {
    private final IPostRepository postRepository;
    private LiveData<List<Post>> items;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        IPostDao postDao = PikabuDB.getDatabase(application).getPostDao();
        postRepository = new PostRepository(postDao);
        // items = postRepository.getAllPosts(0, 5);
    }

    public LiveData<List<Post>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
            loadUsers();
        }
        return items;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }



}


//    private PostItemRepository repository;
//
//    private final LiveData<List<PostItem>> postItemList;
//
//    public HomeViewModel(Application application) {
//        super(application);
//        repository = new PostItemRepository(application);
//        postItemList = repository.getAll();
//    }
//
//    LiveData<List<PostItem>> getAll() { return postItemList; }