package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.model.User;

public class LoginActivity extends AppCompatActivity {
    private UserRoomDatabase db;
    private UserDao userDao;
    private UserRepository respository;
    ViewBinding binding;

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        repository = UserRepository.getRepository(getApplication());

        db = UserRoomDatabase.getDatabase(getApplicationContext());
        userDao = db.userDao();

        // Login button
        Button btnNext = findViewById(R.id.btnNext);

        // Sign up button
        Button btnSignUp = findViewById(R.id.btnSignUp);

        // button listeners
        btnNext.setOnClickListener(v -> login());
        btnSignUp.setOnClickListener(v -> signUpActivity());
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        User user = userDao.login(username, password);

        if (user != null) {
            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }

        assert user != null;
        if (user.isAdmin()) {
            Toast.makeText(LoginActivity.this, "Welcome, admin", Toast.LENGTH_SHORT).show();
            adminActivity();
        } else {
            Toast.makeText(LoginActivity.this, "Welcome, " + username + ". ", Toast.LENGTH_SHORT).show();
            landingActivity();
        }
    }

    // navigation functions
    private void landingActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void adminActivity() {
        Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}