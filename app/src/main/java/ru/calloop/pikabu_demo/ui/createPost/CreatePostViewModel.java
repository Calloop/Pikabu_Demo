package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private IPostRepository repository;
    private MutableLiveData<List<PostItem>> oldPostItemList;
    private MutableLiveData<List<PostItem>> newPostItemList;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        IPostDao IPostDao = PikabuDB.getDatabase(application).getPostDao();
        repository = new PostRepository(IPostDao);
        //postItemList = repository.getPostItems(0, 5);
    }

    public MutableLiveData<List<PostItem>> getOldPostItemList() {
        if (oldPostItemList == null)
        {
            oldPostItemList = new MutableLiveData<>();
        }
        return oldPostItemList;
    }

    public void setOldPostItemList(List<PostItem> oldPostItemList) {
        this.oldPostItemList.postValue(oldPostItemList);
    }

    public MutableLiveData<List<PostItem>> getNewPostItemList() {
        if (newPostItemList == null)
        {
            newPostItemList = new MutableLiveData<>();
        }
        return newPostItemList;
    }

    public void setNewPostItemList(List<PostItem> newPostItemList) {
        this.newPostItemList.postValue(newPostItemList);
    }
}
