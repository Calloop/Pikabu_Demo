package ru.calloop.pikabu_demo.createPostActivity.postItem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostItemDbHelper extends SQLiteOpenHelper {

    public static class DATABASE_DATA {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "pikabuDb";
        public static final String TABLE_POSTITEMS = "postItems";
    }

    public static class COLUMN {
        public static final String ID = "_id";
        public static final String POST_ID = "postId";
        public static final String POSITION = "position";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s integer,"
                            + "%s integer,"
                            + "%s integer,"
                            + "%s text" + ");",
                    DATABASE_DATA.TABLE_POSTITEMS, COLUMN.ID, COLUMN.POST_ID, COLUMN.POSITION,
                    COLUMN.TYPE, COLUMN.VALUE);

    public PostItemDbHelper(Context context) {
        super(context, DATABASE_DATA.DATABASE_NAME, null, DATABASE_DATA.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(String.format("drop table if exists %s",
                DATABASE_DATA.TABLE_POSTITEMS));
        onCreate(sqLiteDatabase);
    }

    public boolean addEntry(PostItemModel postItemModel) {
        return false;
    }

    public boolean deleteEntry(PostItemModel postItemModel) {
        return false;
    }
}
