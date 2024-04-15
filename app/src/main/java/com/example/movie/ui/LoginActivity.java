package com.example.movie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movie.R;
import com.example.movie.data.UserDao;
import com.example.movie.data.UserRoomDatabase;
import com.example.movie.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnNext);

        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        userDao = db.UserDao();

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            User user = userDao.findByUsernameAndPassword(username, password);

            if (user != null) {
                // Login successful
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            } else {
                // Login failed
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}