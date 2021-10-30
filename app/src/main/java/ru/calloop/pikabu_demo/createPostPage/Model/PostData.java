package ru.calloop.pikabu_demo.createPostPage.Model;

public class PostData {

    private int id, postId, dataPosition, dataType;
    private String dataValue;

    public PostData() {
    }

    public PostData(int dataType) {
        this.dataType = dataType;
    }

    public PostData(int id, int postId, int dataPosition, int dataType, String dataValue) {
        this.id = id;
        this.postId = postId;
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
