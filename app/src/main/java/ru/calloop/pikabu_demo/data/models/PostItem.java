package ru.calloop.pikabu_demo.data.models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "postItems",
        foreignKeys = @ForeignKey(entity = Post.class, parentColumns = "id",
                childColumns = "post_id", onDelete = CASCADE))
public class PostItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "post_id", index = true)
    private int postId;
    @ColumnInfo(name = "position")
    private int position;
    @ColumnInfo(name = "type")
    private int type;
    @ColumnInfo(name = "value")
    private String value;

    public PostItem(int type, int position, String value) {
        this.type = type;
        this.position = position;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}