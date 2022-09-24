package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost.ICreatePostPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost.createPostPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.SessionPreferences;

public class CreatePostViewModel extends AndroidViewModel {

    private final IPostRepository postRepository;
    private final ICreatePostPreferences createPostPreferences;
    private final ISessionPreferences sessionPreferences;
    private final MutableLiveData<Integer> type = new MutableLiveData<>();

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        createPostPreferences = new createPostPreferences(application);
        sessionPreferences = new SessionPreferences(application);
    }

    public void insertPostToDB(int accountId, String postHeadline, List<PostItem> postItemList) {
        postRepository.insert(accountId, postHeadline, postItemList);
    }

    public LiveData<Integer> getType() {
        return type;
    }

    public void setType(int type) {
        this.type.postValue(type);
    }

    //region [CREATE POST PREFERENCES]
    public String getPostHeadline() {
        return createPostPreferences.getPostHeadline();
    }

    public List<PostItem> getPostItems() {
        return createPostPreferences.getPostItems();
    }

    public void setPostHeadline(String postHeadline) {
        createPostPreferences.setPostHeadline(postHeadline);
    }

    public void setPostItems(List<PostItem> postItems) {
        createPostPreferences.setPostItems(postItems);
    }

    public void clearPreferences() {
        createPostPreferences.clearPreference();
    }
    //endregion

    public int getAccountId() {
        return sessionPreferences.getAccountId();
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "http://cdn.meme.am/instances/60677654.jpg";
    }

    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_create_post_add_post)
                .into(view);
    }
}