package ru.calloop.pikabu_demo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.calloop.pikabu_demo.ui.models.Post;
import ru.calloop.pikabu_demo.ui.models.PostItem;
import ru.calloop.pikabu_demo.ui.repositories.Account.IAccountDao;
import ru.calloop.pikabu_demo.ui.repositories.Post.IPostDao;
import ru.calloop.pikabu_demo.ui.models.Account;

@Database(entities = {Post.class, PostItem.class, Account.class}, version = PikabuDB.VERSION)
public abstract class PikabuDB extends RoomDatabase {

    static final int VERSION = 1;
    private static PikabuDB INSTANCE;

    public abstract IPostDao getPostDao();
    public abstract IAccountDao getAccountDao();

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
