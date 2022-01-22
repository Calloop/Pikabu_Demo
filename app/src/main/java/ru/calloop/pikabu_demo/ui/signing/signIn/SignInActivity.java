package ru.calloop.pikabu_demo.ui.signing.signIn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.signingActivity.models.SessionManager;

public class SignInActivity extends AppCompatActivity {

    Button buttonSignUp, buttonSendLoginData;
    ImageButton imageButtonBackToMain;
    EditText editTextLogin_sign_in, editTextPassword_sign_in;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_in);

//        Toolbar toolbar = findViewById(R.id.toolbar_signIn);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Авторизация");
//
//        buttonSignUp = findViewById(R.id.buttonSignUp);
//        buttonSendLoginData = findViewById(R.id.buttonSendLoginData);
//
//        imageButtonBackToMain = findViewById(R.id.imageButton_back_to_main_2);
//
//        editTextLogin_sign_in = findViewById(R.id.editTextLogin_sign_in);
//        editTextPassword_sign_in = findViewById(R.id.editTextPassword_sign_in);
//
//        sessionManager = new SessionManager(getApplicationContext());
//
//        buttonSendLoginData.setOnClickListener(v -> {
//            String login = editTextLogin_sign_in.getText().toString().trim();
//            String password = editTextPassword_sign_in.getText().toString().trim();
//
//            if(editTextLogin_sign_in.getText().toString().trim().equalsIgnoreCase("")){
//                editTextLogin_sign_in.setError("Введите логин");
//            }
//            if(editTextPassword_sign_in.getText().toString().trim().equalsIgnoreCase("")){
//                editTextPassword_sign_in.setError("Введите пароль");
//            }
//
//            userDBHelper userDbHelper = new userDBHelper(SignInActivity.this);
//            boolean success = userDbHelper.getOne(login, password);
//
//            if(success == true)
//            {
//                sessionManager.setLogin(true);
//                sessionManager.setUsername(login);
//
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//            else {
//                editTextLogin_sign_in.setError("Ошибка. Вы ввели неверные данные авторизации");
//            }
//        });
//
//        buttonSignUp.setOnClickListener(v -> {
//            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
//            startActivity(intent);
//        });
//
//        imageButtonBackToMain.setOnClickListener(v ->
//        {
//            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//            startActivity(intent);
//        });
    }
}