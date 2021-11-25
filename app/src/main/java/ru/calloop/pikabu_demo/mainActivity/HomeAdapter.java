package ru.calloop.pikabu_demo.mainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.createPostActivity.postItem.PikabuDB;
import ru.calloop.pikabu_demo.createPostActivity.postItem.PostItem;

public class HomeAdapter extends FragmentStateAdapter {

//    PikabuDB database = Room.databaseBuilder(getApplicationContext(), PikabuDB.class, "Pikabu").allowMainThreadQueries().build();
//    postItems = new ArrayList<>(1);
//        postItems.add(new PostItem(1, 1, 1, 1, "lolo"));

    public HomeAdapter(Fragment fragment) {
        super(fragment);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new Tab1MainFragment();
        } else if (position == 1) {
            return new Tab1MainFragment();
        } else if (position == 2) {
            return new Tab1MainFragment();
        } else if (position == 3) {
            return new Tab1MainFragment();
        } else return new Tab1MainFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}