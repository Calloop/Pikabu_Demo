package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.createPost.models.Post;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;
import ru.calloop.pikabu_demo.ui.signing.models.SessionManager;

public class CreatePostViewModel extends AndroidViewModel {

    private final IPostRepository postRepository;
    private MutableLiveData<List<PostItem>> postItems;
    private final SharedPreferences sharedPreferences;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application
                .getSharedPreferences("POST_CREATE_CACHE", Context.MODE_PRIVATE);
        IPostDao postDao = PikabuDB.getDatabase(application).getPostDao();
        postRepository = new PostRepository(postDao);
        //postItemList = repository.getPostItems(0, 5);
    }

//    LiveData<List<PostItem>> getPostItems() {
//        if (postItems == null) {
//            postItems = new MutableLiveData<>();
//            //loadArrayList();
//        }
//        return postItems;
//    }

    public void setPostItems(Post post, List<PostItem> postItems) {
        postRepository.insert(post, postItems);
    }

    @TypeConverter
    public MutableLiveData<List<PostItem>> loadArrayList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("POST", null);
        Type type = new TypeToken<List<PostItem>>() {
        }.getType();

        return json == null ? new MutableLiveData<>() : new MutableLiveData<>(gson.fromJson(json,
                type));
    }

    public void setArrayList(List<PostItem> list) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("POST", json);
        editor.apply();
    }

    public void clearArrayList() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private final MutableLiveData<Integer> state = new MutableLiveData<>();

    public MutableLiveData<Integer> getState() {
        return state;
    }

    public void setState(int type) {
        state.postValue(type);
    }
}
