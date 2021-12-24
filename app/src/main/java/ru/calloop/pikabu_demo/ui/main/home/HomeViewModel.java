package ru.calloop.pikabu_demo.ui.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> state;

    public MutableLiveData<Integer> getState() {
        if (state == null)
        {
            state = new MutableLiveData<>();
        }
        return state;
    }

    public void setState(int type) {
        state.setValue(type);
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