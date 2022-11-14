package ru.calloop.pikabu_demo.di.components;

import javax.inject.Singleton;

import dagger.Component;
import ru.calloop.pikabu_demo.di.module.AppModule;
import ru.calloop.pikabu_demo.ui.fragments.main.home.tabs.Tab1MainFragment;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
//    void inject(Tab1MainFragment tab1MainFragment);

//    PostItemDao postItemDao();
//
//    PikabuDB database();
//
//    PostItemRepositoryImpl postItemRepository();
//
//    Application application();
}
