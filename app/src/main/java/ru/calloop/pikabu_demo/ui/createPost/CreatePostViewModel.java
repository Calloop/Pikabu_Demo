package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private IPostRepository repository;
    private MutableLiveData<List<PostItem>> localPostItemList;
    private MutableLiveData<List<PostItem>> dbPostItemList;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        IPostDao IPostDao = PikabuDB.getDatabase(application).getPostDao();
        repository = new PostRepository(IPostDao);
        //postItemList = repository.getPostItems(0, 5);
    }

    public MutableLiveData<List<PostItem>> getLocalPostItemList() {
        if (localPostItemList == null)
        {
            localPostItemList = new MutableLiveData<>();
        }
        return localPostItemList;
    }

    public void setLocalPostItemList(List<PostItem> localPostItemList) {
        this.localPostItemList.postValue(localPostItemList);
    }

    public MutableLiveData<List<PostItem>> getDbPostItemList() {
        if (dbPostItemList == null)
        {
            dbPostItemList = new MutableLiveData<>();
        }
        return dbPostItemList;
    }

    public void setDbPostItemList(List<PostItem> dbPostItemList) {
        this.dbPostItemList.postValue(dbPostItemList);
    }
}
