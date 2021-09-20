package ru.calloop.pikabu_demo.adapter;

public class TypeB implements ListItemCreatePost {
    int type;
    long id;

    public TypeB(int type, long id) {
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