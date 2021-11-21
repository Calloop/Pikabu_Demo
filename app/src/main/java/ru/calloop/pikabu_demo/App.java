package ru.calloop.pikabu_demo;

import android.app.Application;
import android.content.Context;

import ru.calloop.pikabu_demo.di.components.AppComponent;
import ru.calloop.pikabu_demo.di.components.DaggerAppComponent;
import ru.calloop.pikabu_demo.di.module.AppModule;
import ru.calloop.pikabu_demo.di.module.RoomModule;

public class App extends Application {
    private static AppComponent appComponent;

    public static App getComponent(Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        appComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .roomModule(new RoomModule(this))
//                .build();
//        appComponent.inject(this);
    }
}
