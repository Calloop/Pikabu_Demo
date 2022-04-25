package ru.calloop.pikabu_demo.ui.repositories.Account;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import ru.calloop.pikabu_demo.ui.models.Account;

@Dao
public interface IAccountDao {
    String LOGIN = "LOGIN";
    String EMAIL = "EMAIL";

    @Transaction
    default String checkAccountExists(String login, String email) {
        if (getLogin(login)) {
            return LOGIN;
        }
        if (getEmail(email)) {
            return EMAIL;
        }
        return null;
    }

//    @Transaction
//    default LiveData<Account> doLogin(String loginOrEmail, String password) {
//        int id = getAccountId(loginOrEmail, password);
//        if (id != 0) {
//            return getAccount(id);
//        }
//        return null;
//    }

    @Query("SELECT login FROM accounts WHERE login = :login LIMIT 1")
    boolean getLogin(String login);

    @Query("SELECT email FROM accounts WHERE email = :email LIMIT 1")
    boolean getEmail(String email);

    @Query("SELECT id FROM accounts WHERE login = :loginOrEmail OR email = " +
            ":loginOrEmail LIMIT 1")
    boolean checkLoginOrEmailExists(String loginOrEmail);

    @Query("SELECT id FROM accounts WHERE password = :password LIMIT 1")
    int checkPasswordIsCorrect(String password);

//    @Query("SELECT * FROM accounts WHERE id = :id")
//    LiveData<Account> getAccount(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createAccount(Account account);
}
