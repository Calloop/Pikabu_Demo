package ru.calloop.pikabu_demo.adapter;

public class TypeA implements ListItemCreatePost {
    int type;
    long id;

    public TypeA(long id, int type) {
        this.type = type;
        this.id = id;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public long getID() {
        return id;
    }

    public void setItemType(int type) {
        this.type = type;
    }

    public void setID(long id) {
        this.id = id;
    }
}