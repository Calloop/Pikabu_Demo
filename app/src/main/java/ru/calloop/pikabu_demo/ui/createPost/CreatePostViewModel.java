package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private IPostRepository repository;
    private LiveData<List<PostItem>> postItemList;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        IPostDao IPostDao = PikabuDB.getDatabase(application).getPostDao();
        repository = new PostRepository(IPostDao);
        //postItemList = repository.getPostItems(0, 5);
    }

//    public MutableLiveData<List<PostItem>> getPostItemList() {
//        if (postItemList == null)
//        {
//            postItemList = new MutableLiveData<>();
//        }
//        return postItemList;
//    }

    //public void setPostItemList(List<PostItem> postItemList) {
//        postItemList.setValue(type);
//    }
}
