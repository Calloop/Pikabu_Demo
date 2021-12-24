package ru.calloop.pikabu_demo.createPostActivity.models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import ru.calloop.pikabu_demo.signingActivity.models.Account;

@Entity(tableName = "posts",
        foreignKeys = @ForeignKey(entity = Account.class, parentColumns = "id",
                childColumns = "user_id", onDelete = CASCADE))
public class Post {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "user_id")
    private long userId;

    public Post(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
