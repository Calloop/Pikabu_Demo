package ru.calloop.pikabu_demo.mainActivity;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import ru.calloop.pikabu_demo.createPostActivity.CreatePostContract;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemModel;

public class MainPresenter implements MainContract.IPresenter {
    private MainContract.IView view;
    private final PostItemModel postItemModel;

    public MainPresenter(PostItemModel postItemModel) {
        this.postItemModel = postItemModel;
    }

    public void attachView(MainContract.IView view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadPostItems();
    }

    public void loadPostItems() {
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