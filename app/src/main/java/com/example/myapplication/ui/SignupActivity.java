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
    private EditText etUsername, etPassword, etConfirmPassword;
    private Boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etcPassword);
        Button btnSignup = findViewById(R.id.btnSignUp);
        Button btnBack = findViewById(R.id.btnBackToSignIn);
        btnSignup.setOnClickListener(v -> signupUser());
        btnSignup.setOnLongClickListener(v -> {
            isAdmin = true;
            signupUser();
            return true;
        });
        btnBack.setOnClickListener(v -> signIn());

    }

    private void signupUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // input validation
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

        // Create new user and add to db
        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        UserDao userDao = db.userDao();
        User user = userDao.findByUsername(username);

        // haha good luck reading this
        // first we check that the sign up was successful, ie the insert returned a non-null user class
        if (user == null) {
            user = new User(username, password, isAdmin);
            if (userDao.signUp(user) == -1) {
                if(isAdmin) {
                    admin();
                } else {
                    landing();
                }
                Toast.makeText(SignupActivity.this, "Welcome " + username + ". ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void landing() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signIn() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void admin() {
        Intent intent = new Intent(SignupActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }
}