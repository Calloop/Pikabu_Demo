package ru.calloop.pikabu_demo.di.module;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.calloop.pikabu_demo.PikabuDB;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostRepository;
import ru.calloop.pikabu_demo.ui.repositories.Post.PostRepository;

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
    IPostDao providesPostDao(PikabuDB database) {
        return database.getPostDao();
    }

    @Singleton
    @Provides
    IPostRepository providesPostRepository() {
        return new PostRepository(database.getPostDao());
    }
}
