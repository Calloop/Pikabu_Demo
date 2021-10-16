package ru.calloop.pikabu_demo.userAccountPage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.calloop.pikabu_demo.userAccountPage.models.AccountModel;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, "PikabuDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + TABLE_ACCOUNTS + " (" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LOGIN + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " PASSWORD)";

        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
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

        if (insert == -1) {
            return false;
        } else
            return true;
    }

    public boolean getOne(String login, String password) {
        String queryString = "SELECT login FROM " + TABLE_ACCOUNTS + " WHERE login = ? AND password = ?";
        SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(queryString, new String[] {login, password});

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        else {
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
