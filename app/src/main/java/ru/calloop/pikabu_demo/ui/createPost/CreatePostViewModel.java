package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;

import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private final IPostRepository postRepository;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
    }

    public void insertPostToDB(int accountId, String postHeadline, List<PostItem> postItemList) {
        postRepository.insert(accountId, postHeadline, postItemList);
    }
}