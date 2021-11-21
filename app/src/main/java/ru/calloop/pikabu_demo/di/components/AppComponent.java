package ru.calloop.pikabu_demo.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDao;
import ru.calloop.pikabu_demo.di.module.AppModule;
import ru.calloop.pikabu_demo.di.module.RoomModule;
import ru.calloop.pikabu_demo.mainActivity.MainActivity;
import ru.calloop.pikabu_demo.mainActivity.Tab1MainFragment;
import ru.calloop.pikabu_demo.services.impl.PostItemRepositoryImpl;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(Tab1MainFragment tab1MainFragment);

//    PostItemDao postItemDao();
//
//    PikabuDB database();
//
//    PostItemRepositoryImpl postItemRepository();
//
//    Application application();
}
