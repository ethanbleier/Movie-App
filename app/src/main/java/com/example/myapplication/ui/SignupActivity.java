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
    private final Checkable etIsAdmin = findViewById(R.id.etIsAdmin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        UserDao userDao = db.userDao();

        // views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etcPassword);
        Button btnSignup = findViewById(R.id.btnSignUp);
        Button btnBack = findViewById(R.id.btnBackToSignIn);
        btnSignup.setOnClickListener(v -> signupUser());
        btnBack.setOnClickListener(v -> goSignIn());

    }

    private void signupUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        boolean isAdmin = Boolean.parseBoolean(etIsAdmin.toString().trim());

        // input validation
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(SignupActivity.this, "fill in all fields please", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new user and add to db
    }

    private void goMain() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goSignIn() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}