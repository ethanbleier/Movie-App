package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast; // oml
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.data.model.User;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserRepository repository;

    private static final String LOGIN_ACTIVITY_USER_ID = "LOGIN_ACTIVITY_USER_ID";

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new UserRepository(getApplication());


        // Go to Sign Up Button Listener
        binding.btnSignUp.setOnClickListener(v -> signUpActivity());

        // Next/sign in Button Listener
        binding.btnNext.setOnClickListener(v -> getFieldFromDisplayAndLogin());
    }

    static Intent loginActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(String.valueOf(LOGIN_ACTIVITY_USER_ID), userId);
        return intent;
    }

    // here we return true if the username or password inputted is valid.
    // if the user/pwd is invalid, validate returns false
    private boolean validInput(String text) {
        if (text == null) {
            return false;
        }

        // after confirming text is not null,
        // we return true if the text is not blank.
        return !text.isEmpty();
    }

    // Method to read data from the text fields
    private void getFieldFromDisplayAndLogin() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (validInput(username) && validInput(password)) {
            this.username = username;
            this.password = password;
            login();
        } else {
            if (!validInput(username)) {
                Toast.makeText(LoginActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
            }
            if (!validInput(password)) {
                Toast.makeText(LoginActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Primary Login Method
    private void login() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            User user = repository.findByUsernameAndPassword(username, password);

            if (user != null) {
                // Login successful
                runOnUiThread(() -> {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    if (user.isAdmin()) {
                        adminActivity();
                    } else {
                        landingActivity();
                    }
                });
            } else {
                // Login failed
                runOnUiThread(() -> {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    /**
     * ==== Navigation methods ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ====
     */

    private void landingActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void adminActivity() {
        Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
        startActivity(intent);
    }

    private void signUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}