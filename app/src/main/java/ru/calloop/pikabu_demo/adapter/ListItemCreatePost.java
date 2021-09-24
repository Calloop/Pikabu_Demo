package ru.calloop.pikabu_demo.adapter;

public interface ListItemCreatePost {
    int TYPE_A = 1;
    int TYPE_B = 2;
    int TYPE_C = 3;

    long getID();
    int getItemType();
}