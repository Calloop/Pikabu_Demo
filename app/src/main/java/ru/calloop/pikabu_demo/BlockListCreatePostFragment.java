package ru.calloop.pikabu_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import java.util.Objects;

public class BlockListCreatePostFragment extends ListFragment {

    Button buttonAddTextCreatePost;

    String[] data = new String[]{"one", "two", "three", "four"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_content_create_post, container, false);

        buttonAddTextCreatePost = requireActivity().findViewById(R.id.button_add_text_create_post);

        buttonAddTextCreatePost.setOnClickListener(v ->
        {
            Log.e("button", "pressed");
            Toast.makeText(getActivity(), "BUTTON IS PRESSED", Toast.LENGTH_SHORT).show();
            //textViewDescriptionCreatePost.setVisibility(View.GONE);
            //loadFragment(new CreatePostAddBlockFragment());
        });

        return view;
    }
}