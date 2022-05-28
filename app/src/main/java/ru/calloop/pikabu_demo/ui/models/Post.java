package ru.calloop.pikabu_demo.ui.models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts",
        foreignKeys = @ForeignKey(entity = Account.class, parentColumns = "id",
                childColumns = "user_id", onDelete = CASCADE))
public class Post {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "headline")
    private String headline;

    public Post(int userId, String headline) {
        this.userId = userId;
        this.headline = headline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
