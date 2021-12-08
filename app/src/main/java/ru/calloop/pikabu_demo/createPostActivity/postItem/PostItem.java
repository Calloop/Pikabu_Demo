package ru.calloop.pikabu_demo.createPostActivity.postItem;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "postItems", indices = {@Index(value = "post_id", unique = true)},
        foreignKeys = @ForeignKey(entity = Post.class, parentColumns = "id",
                childColumns = "post_id", onDelete = CASCADE))
public class PostItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "post_id")
    private int postId;
    @ColumnInfo(name = "data_position")
    private int dataPosition;
    @ColumnInfo(name = "data_type")
    private int dataType;
    @ColumnInfo(name = "data_value")
    private String dataValue;

    public PostItem(int dataPosition, int dataType, String dataValue) {
        this.dataPosition = dataPosition;
        this.dataType = dataType;
        this.dataValue = dataValue;
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

    public int getDataPosition() {
        return dataPosition;
    }

    public void setDataPosition(int dataPosition) {
        this.dataPosition = dataPosition;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
