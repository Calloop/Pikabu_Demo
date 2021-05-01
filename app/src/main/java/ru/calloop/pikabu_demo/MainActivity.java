package ru.calloop.pikabu_demo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.calloop.pikabu_demo.adapter.SessionManager;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbarMain;
    Button buttonSignIn;
    ImageButton imageButtonCreatePost;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = findViewById(R.id.toolbar_main);

        sessionManager = new SessionManager(getApplicationContext());

//        if (sessionManager.getLogin() == true)
//        {
//            buttonSignIn.setText(sessionManager.getUsername());
//            imageButtonCreatePost.setVisibility(View.VISIBLE);
//            Toast.makeText(MainActivity.this, "Пользователь " + sessionManager.getUsername(), Toast.LENGTH_SHORT).show();
//        } else
//        {
//            imageButtonCreatePost.setVisibility(View.GONE);
//            Toast.makeText(MainActivity.this, "Пользователь не авторизован", Toast.LENGTH_SHORT).show();
//        }

//        buttonSignIn.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//            startActivity(intent);
//        });
//
//        imageButtonCreatePost.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
//            startActivity(intent);
//        });
    }
}