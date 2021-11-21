package ru.calloop.pikabu_demo.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItemDao;
import ru.calloop.pikabu_demo.services.PostItemRepository;
import ru.calloop.pikabu_demo.services.impl.PostItemRepositoryImpl;

@Module
public class RoomModule {

    private Context context;
    private PikabuDB database;

    public RoomModule(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, PikabuDB.class, "Pikabu").build();
    }

    @Singleton
    @Provides
    PikabuDB providesRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    PostItemDao providesPostItemDao(PikabuDB database) {
        return database.getPostItemDao();
    }

    @Singleton
    @Provides
    PostItemRepository providesPostItemRepository() {
        return new PostItemRepositoryImpl(context);
    }
}
