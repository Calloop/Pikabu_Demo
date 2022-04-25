package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private final IPostRepository postRepository;
    private final MutableLiveData<List<PostItem>> postItems;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        postItems = new MutableLiveData<>();
    }

    public void setPostItems(List<PostItem> postItems) {
        this.postItems.postValue(postItems);
    }

    public void insertPostToDB(long accountId, String postHeadline) {
        postRepository.insertPost(accountId, postHeadline);
        postRepository.insertPostItemList(postItems.getValue());
    }
}
