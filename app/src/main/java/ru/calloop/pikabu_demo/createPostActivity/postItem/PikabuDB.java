package ru.calloop.pikabu_demo.createPostActivity.postItem;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {Post.class, PostItem.class}, version = PikabuDB.VERSION)
public abstract class PikabuDB extends RoomDatabase {

    static final int VERSION = 1;
    private static PikabuDB INSTANCE;

    public abstract PostDao getPostDao();

    public static PikabuDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PikabuDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PikabuDB.class, "Pikabu")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
