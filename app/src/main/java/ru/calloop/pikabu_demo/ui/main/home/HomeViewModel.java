package ru.calloop.pikabu_demo.ui.main.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<Integer> state = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getState() {
        return state;
    }

    public void setState(int type) {
        state.postValue(type);
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