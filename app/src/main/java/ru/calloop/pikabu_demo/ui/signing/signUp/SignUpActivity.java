package ru.calloop.pikabu_demo.ui.signing.signUp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import ru.calloop.pikabu_demo.R;

public class SignUpActivity extends AppCompatActivity {

    Button buttonSignInFromSignUpActivity, buttonSignUpCreateAccount;
    ImageButton imageButtonBackToMain;
    EditText editTextLogin, editTextPhone, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

//        Toolbar toolbar = findViewById(R.id.toolbar_signUp);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Регистрация");

//        buttonSignInFromSignUpActivity = findViewById(R.id.button_sign_in_from_sign_up_activity);
//        buttonSignUpCreateAccount = findViewById(R.id.button_sign_up_create_account);
//
//        imageButtonBackToMain = findViewById(R.id.imageButton_back_to_main);
//
//        editTextLogin = findViewById(R.id.editTextLogin);
//        editTextPhone = findViewById(R.id.editTextPhone);
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//
//        buttonSignInFromSignUpActivity.setOnClickListener(v -> {
//            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//        startActivity(intent);
//        });
//
//        buttonSignUpCreateAccount.setOnClickListener(v -> {
//            Account account;
//
//            try {
//                account = new Account(editTextLogin.getText().toString(), editTextPassword.getText().toString());
//                Toast.makeText(SignUpActivity.this, account.toString(), Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                Toast.makeText(SignUpActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
//                account = new Account("error", "error");
//            }
//
//            userDBHelper userDbHelper = new userDBHelper(SignUpActivity.this);
//            boolean success = userDbHelper.addOne(account);
//            Toast.makeText(SignUpActivity.this, "Success: " + success, Toast.LENGTH_SHORT).show();
//        });
//
//        imageButtonBackToMain.setOnClickListener(v ->
//        {
//            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//            startActivity(intent);
//        });
    }
}