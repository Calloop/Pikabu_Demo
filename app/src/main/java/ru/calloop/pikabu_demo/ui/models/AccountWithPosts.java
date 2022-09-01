package ru.calloop.pikabu_demo.ui.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AccountWithPosts {
    @Embedded
    public Account account;

    @Relation(
            parentColumn = "id",
            entity = Post.class,
            entityColumn = "user_id"
    )
    public List<Post> postsList;
}