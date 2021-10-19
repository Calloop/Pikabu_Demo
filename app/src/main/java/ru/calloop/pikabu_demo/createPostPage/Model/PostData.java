package ru.calloop.pikabu_demo.createPostPage.Model;

public class PostData {

    int type, image;
    String text;

    public PostData(int type, int image, String text) {
        this.type = type;
        this.text = text;
        this.image = image;

    }

    public int getType() {
        return type;
    }

    public void setType(int id) {
        this.type = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}