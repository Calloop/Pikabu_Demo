package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.internal.operators.single.SingleZipIterable;
import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.models.PostDao;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;
import ru.calloop.pikabu_demo.ui.signing.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.signing.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private IPostRepository repository;
    private LiveData<List<PostItem>> postItemList;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        PostDao postDao = PikabuDB.getDatabase(application).getPostDao();
        repository = new PostRepository(postDao);
        //postItemList = repository.getPostItems(0, 5);
    }

    public MutableLiveData<List<PostItem>> getPostItemList() {
        if (postItemList == null)
        {
            postItemList = new MutableLiveData<>();
        }
        return postItemList;
    }

    public void setPostItemList(List<PostItem> postItemList) {
        postItemList.setValue(type);
    }
}
