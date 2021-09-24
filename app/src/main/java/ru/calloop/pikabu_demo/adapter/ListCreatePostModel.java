package ru.calloop.pikabu_demo.adapter;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListCreatePostModel {

    ArrayList<ListItemCreatePost> list;

    public ListCreatePostModel()
    {
        this.list = new ArrayList<>();
    }

    public List<ListItemCreatePost> getList() {
        return list;
    }
}