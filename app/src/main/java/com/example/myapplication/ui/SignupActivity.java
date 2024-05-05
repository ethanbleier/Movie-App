package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.model.User;

public class SignupActivity extends AppCompatActivity {
    private String username, password, confirmedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // views
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etcPassword);

        Button btnSignup = findViewById(R.id.btnSignUp);

        // one liner to go back to sing in page :)
        findViewById(R.id.btnBackToSignIn).setOnClickListener(v -> signInActivity());

        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmedPassword = etConfirmPassword.getText().toString().trim();

        btnSignup.setOnClickListener(v -> {
            if(!performInputValidation(username, password, confirmedPassword)) {
                signupUser(username, password, confirmedPassword);
            }
        });
    }

    private boolean performInputValidation(String username, String password, String confirmedPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!confirmedPassword.equals(password)) {
            Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void signupUser(String username, String password, String confirmedPassword) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        UserDao userDao = db.userDao();
        User user = userDao.findByUsername(username);

        // haha good luck reading this
        // first we check that the sign up was successful, ie the insert returned a non-null user class
        if (user == null) {
            user = new User(username, password);
            if (userDao.signUp(user) != -1) {
                // need to figure out admin logic.
                Toast.makeText(SignupActivity.this, "Welcome " + username + ". ", Toast.LENGTH_SHORT).show();
                landingActivity();
            } else {
                Toast.makeText(SignupActivity.this, "Error signing you in", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void landingActivity() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signInActivity() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void restartActivity() {
        Intent intent = new Intent(SignupActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    private void adminActivity() {
        Intent intent = new Intent(SignupActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }
}