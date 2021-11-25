package ru.calloop.pikabu_demo.mainActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public class MainPresenter implements MainContract.IPresenter {
    MutableLiveData<List<PostItem>> postItemList = new MutableLiveData();

    void init

    {
        loadPostItemList();
    }

//    public void attachView(MainContract.IView view) {
//        this.view = view;
//    }
//
//    public void detachView() {
//        view = null;
//    }
//
//    public void viewIsReady() {
//        loadPostItems();
//    }

    public LiveData<List<PostItem>> getPostItemList() {
        return postItemList;
    }

    public void loadPostItemList() {
        //getObservable().subscribeWith(getObserver());
        //postItemModel.loadPostItems(items -> view.showPostItems(items));
    }

//    public Observable<List<PostItem>> getObservable(){
//        return NetworkClient.getRetrofit().create(NetworkInterface.class)
//                .getMovies("004cbaf19212094e32aa9ef6f6577f22")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public DisposableObserver<List<PostItem>> getObserver(){
//        return new DisposableObserver<List<PostItem>>() {
//
//            @Override
//            public void onNext(@NonNull PostItem postItem) {
////                Log.d(TAG,"OnNext"+movieResponse.getTotalResults());
////                mvi.displayMovies(movieResponse);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
////                Log.d(TAG,"Error"+e);
////                e.printStackTrace();
////                mvi.displayError("Error fetching Movie Data");
//            }
//
//            @Override
//            public void onComplete() {
////                Log.d(TAG,"Completed");
//            }
//        };
//    }
}