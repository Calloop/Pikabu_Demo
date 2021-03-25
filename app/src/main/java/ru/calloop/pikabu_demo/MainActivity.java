package ru.calloop.pikabu_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button buttonSignIn;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = findViewById(R.id.buttonSignIn);

        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.getLogin() == true)
        {
            buttonSignIn.setText(sessionManager.getUsername());
            Toast.makeText(MainActivity.this, "Пользователь " + sessionManager.getUsername(), Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(MainActivity.this, "Пользователь не авторизован", Toast.LENGTH_SHORT).show();
        }

        buttonSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }
}