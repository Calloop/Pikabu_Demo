package ru.calloop.pikabu_demo.userAccountPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.mainPage.MainActivity;
import ru.calloop.pikabu_demo.userAccountPage.models.AccountModel;

public class SignUpActivity extends AppCompatActivity {

    Button buttonSignInFromSignUpActivity, buttonSignUpCreateAccount;
    ImageButton imageButtonBackToMain;
    EditText editTextLogin, editTextPhone, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonSignInFromSignUpActivity = findViewById(R.id.button_sign_in_from_sign_up_activity);
        buttonSignUpCreateAccount = findViewById(R.id.button_sign_up_create_account);

        imageButtonBackToMain = findViewById(R.id.imageButton_back_to_main);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonSignInFromSignUpActivity.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        });

        buttonSignUpCreateAccount.setOnClickListener(v -> {
            AccountModel accountModel;

            try {
                accountModel = new AccountModel(-1, editTextLogin.getText().toString(), editTextPhone.getText().toString(), editTextEmail.getText().toString(), editTextPassword.getText().toString());
                Toast.makeText(SignUpActivity.this, accountModel.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(SignUpActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                accountModel = new AccountModel(-1, "error", "error", "error", "error");
            }

            DBHelper dbHelper = new DBHelper(SignUpActivity.this);
            boolean success = dbHelper.addOne(accountModel);
            Toast.makeText(SignUpActivity.this, "Success: " + success, Toast.LENGTH_SHORT).show();
        });

        imageButtonBackToMain.setOnClickListener(v ->
        {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}