package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast; // oml

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.model.User;

public class LoginActivity extends AppCompatActivity {
    private UserRoomDatabase db;
    private UserDao userDao;
    private ActivityLoginBinding binding;

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserRepository repository = new UserRepository(getApplication());

        // Next/sign in Button Listener
        binding.btnNext.setOnClickListener(v -> getFieldFromDisplay());

        // Go to Sign Up Button Listener
        binding.btnSignUp.setOnClickListener(v -> signUpActivity());
    }

    // here we return true if the username or password inputted is valid.
    // if the user/pwd is invalid, validate returns false
    private boolean validate(String text) {
        if (text == null) {
            return false;
        }

        return !text.isEmpty();
    }

    // Method to read data from the text fields
    private void getFieldFromDisplay() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (validate(username) && validate(password)) {
            this.username = username;
            this.password = password;

            login();
        }

        if (!validate(username)) {
            Toast.makeText(LoginActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
        }

        if (!validate(password)) {
            Toast.makeText(LoginActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
//            binding.etPassword.setError("Invalid password!");
        }
    }

    // Primary Login Method
    private void login() {
        User user = UserRepository.findByUsernameAndPassword(this.username, this.password);

        if (user != null) {
            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Error in login(): User attempting to add is null", Toast.LENGTH_SHORT).show();
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

    /**
     *  ==== Navigation methods ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ====
     */

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