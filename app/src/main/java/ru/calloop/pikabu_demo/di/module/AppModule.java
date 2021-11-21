package ru.calloop.pikabu_demo.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.calloop.pikabu_demo.services.PostItemRepository;
import ru.calloop.pikabu_demo.services.impl.PostItemRepositoryImpl;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return this.context;
    }

//    PostItemRepository providesPostItemRepository(){
//        return new PostItemRepositoryImpl(context);
//    }
}
