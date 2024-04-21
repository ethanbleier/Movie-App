package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.model.User;
import com.example.myapplication.R;
import com.example.myapplication.model.UserManager;

public class LoginActivity extends AppCompatActivity {
    private UserDao userDao;
    private UserRepository userRepository;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userRepository = new UserRepository(getApplication());
        userRepository.deleteAllUsers();

        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        userDao = db.userDao();

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnNext);
        Button btnSignUp = findViewById(R.id.btnSignUp);

        // sign up button listener
        btnSignUp.setOnClickListener(v -> navigateToSignUpActivity());

        // login button listener
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            boolean loggedIn = UserManager.login(username, password);

            if (loggedIn) {
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // navigation functions
    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}