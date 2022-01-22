package ru.calloop.pikabu_demo.ui.createPost;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.createPostActivity.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private IPostRepository repository;
    private MutableLiveData<List<PostItem>> postItemList;
    private final SharedPreferences sharedPreferences;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application
                .getSharedPreferences("POST_CREATE_CACHE", Context.MODE_PRIVATE);
//        IPostDao IPostDao = PikabuDB.getDatabase(application).getPostDao();
//        repository = new PostRepository(IPostDao);
        //postItemList = repository.getPostItems(0, 5);
    }

    MutableLiveData<List<PostItem>> getList() {
        if (postItemList == null) {
            postItemList = new MutableLiveData<>();
            //loadArrayList();
        }
        return postItemList;
    }

    public List<PostItem> loadArrayList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("POST", null);
        Type type = new TypeToken<List<PostItem>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void setArrayList(List<PostItem> list) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("POST", json);
        editor.apply();
    }
}
