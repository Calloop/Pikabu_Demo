package ru.calloop.pikabu_demo.ui.mainActivity;

import java.util.List;


import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.models.Post;
import ru.calloop.pikabu_demo.createPostActivity.models.PostAndPostItem;
import ru.calloop.pikabu_demo.ui.signing.Post.PostRepository;

public class MainPresenter implements MainContract.IPresenter {
    private MainContract.IView view;
    //MutableLiveData<List<PostItem>> postItemList = new MutableLiveData<>();
    private PostRepository repository;

    public MainPresenter() {

    }

    public void attachView(MainContract.IView view) {
        this.view = view;
        PikabuDB database = PikabuDB.getDatabase(this.view.getContext());
        repository = new PostRepository(database.getPostDao());
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadAll();
    }

    @Override
    public List<PostAndPostItem> getPostItems(int startPosition, int limitCount) {
        return repository.getPostItems(startPosition, limitCount);
    }

    public List<Post> getAllPosts(int startPosition, int limitCount) {
        return repository.getAllPosts(startPosition, limitCount);
    }

//    @Override
//    public void deleteAll(List<PostItem> postItemList)
//    {
//        repository.deleteAll(postItemList);
//    }

//    public void insertItem(PostItem postItem) {
//        repository.insertItem(postItem);
//    }

    public void loadAll() {


//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//
//        Disposable disposable = interactor.getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(list -> postItemList.postValue(list), error -> Log.d("RxJava", "Error getting info from interactor into presenter"));
//
//        compositeDisposable.add(disposable);
//        compositeDisposable.dispose();

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