package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast; // oml

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.model.User;

public class LoginActivity extends AppCompatActivity {
    private @NonNull ActivityLoginBinding binding;

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getFieldFromDisplay();

        // Go to Sign Up Button Listener
        binding.btnSignUp.setOnClickListener(v -> signUpActivity());

        // Next/sign in Button Listener
        binding.btnNext.setOnClickListener(v -> login());
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
    private void getFieldFromDisplay() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (validInput(username) && validInput(password)) {
            this.username = username;
            this.password = password;

            login();
        }

        if (!validInput(username)) {
            Toast.makeText(LoginActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
        }

        if (!validInput(password)) {
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