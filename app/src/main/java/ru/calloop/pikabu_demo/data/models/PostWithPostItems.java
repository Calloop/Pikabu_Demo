package ru.calloop.pikabu_demo.data.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class PostWithPostItems implements Serializable {
    @Embedded
    public Post post;

    @Relation(
            parentColumn = "id",
            entity = PostItem.class,
            entityColumn = "post_id"
    )
    public List<PostItem> postItemList;
}
