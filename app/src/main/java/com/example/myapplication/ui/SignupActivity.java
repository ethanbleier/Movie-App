package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.model.User;

public class SignupActivity extends AppCompatActivity {
    private String username, password, confirmPassword;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // views
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etcPassword);

        Button btnSignup = findViewById(R.id.btnSignUp);
        Button btnBack = findViewById(R.id.btnBackToSignIn);

        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirmPassword.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSignup.setOnClickListener(v -> signupUser());
        btnBack.setOnClickListener(v -> signInActivity());
    }

    private void signupUser() {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        UserDao userDao = db.userDao();
        User user = userDao.findByUsername(username);

        // haha good luck reading this
        // first we check that the sign up was successful, ie the insert returned a non-null user class
        if (user == null) {
            user = new User(username, password, false);
            if (userDao.signUp(user) == -1) {
                if(isAdmin) {
                    adminActivity();
                } else {
                    landingActivity();
                }
                Toast.makeText(SignupActivity.this, "Welcome " + username + ". ", Toast.LENGTH_SHORT).show();
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

    private void adminActivity() {
        Intent intent = new Intent(SignupActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }
}