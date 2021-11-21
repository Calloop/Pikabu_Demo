package ru.calloop.pikabu_demo.model.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.calloop.pikabu_demo.model.user.AccountModel;

public class userDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public userDBHelper(Context context) {
        super(context, "Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = String.format("CREATE TABLE %s (id integer PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT,%s TEXT,%s TEXT,%s PASSWORD)", TABLE_ACCOUNTS, COLUMN_LOGIN,
                COLUMN_PHONE, COLUMN_EMAIL, COLUMN_PASSWORD);

        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_ACCOUNTS));
        onCreate(db);
    }

    public boolean addOne(AccountModel accountModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LOGIN, accountModel.getLogin());
        cv.put(COLUMN_PHONE, accountModel.getPhone());
        cv.put(COLUMN_EMAIL, accountModel.getEmail());
        cv.put(COLUMN_PASSWORD, accountModel.getPassword());

        long insert = db.insert(TABLE_ACCOUNTS, null, cv);

        return insert != -1;
    }

    public boolean getOne(String login, String password) {
        String queryString = String.format("SELECT login FROM %s WHERE login = ? AND password = ?",
                TABLE_ACCOUNTS);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{login, password});

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

//    public List<AccountModel> getAll() {
//        List<AccountModel> returnList = new ArrayList<>();
//
//        String queryString = "SELECT * FROM " + TABLE_ACCOUNTS;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(queryString, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int accountId = cursor.getInt(0);
//                String accountLogin = cursor.getString(1);
//                String accountPhone = cursor.getString(2);
//                String accountEmail = cursor.getString(3);
//                String accountPassword = cursor.getString(4);
//
//                AccountModel newAccount = new AccountModel(accountId, accountLogin, accountPhone, accountEmail, accountPassword);
//                returnList.add(newAccount);
//            } while (cursor.moveToNext());
//        } else {
//
//        }
//
//        cursor.close();
//        db.close();
//        return returnList;
//    }
}
