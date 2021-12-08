package ru.calloop.pikabu_demo.createPostActivity.postItem;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PostAndPostItem {
    @Embedded
    public Post post;
    @Relation(
            parentColumn = "id",
            entity = PostItem.class,
            entityColumn = "post_id"
    )
    public List<PostItem> postItemList;
}
