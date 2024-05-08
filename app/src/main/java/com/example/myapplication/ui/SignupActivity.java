package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivitySignupBinding;
import com.example.myapplication.data.model.User;

public class SignupActivity extends AppCompatActivity {
    private String username = "";
    private String password = "";

    private ActivitySignupBinding binding;
    private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new UserRepository(getApplication());

        binding.btnSignUp.setOnClickListener(v -> getFieldFromDisplayAndSignUp());
        // Back to sign up page navigation
        binding.btnBackToSignIn.setOnClickListener(v -> signInActivity());
    }

    // here we return true if the username or password inputted is valid.
    // if the user/pwd is invalid, validate returns false
    private boolean validInput(String text) {
        return text != null && !text.isEmpty();
    }

    // Method to read data from the text fields
    private void getFieldFromDisplayAndSignUp() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();
        String confirmedPassword = binding.etcPassword.getText().toString();

        if (validInput(username) && validInput(password) & validInput(confirmedPassword)) {
            this.username = username;
            this.password = password;
            signUp();
        } else {
            if (!validInput(username)) {
                Toast.makeText(SignupActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
            }
            if (!validInput(password) || !validInput(confirmedPassword)) {
                Toast.makeText(SignupActivity.this, "Invalid password/s", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void signUp() {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            // Check if the username already exists in the database
            User existingUser = repository.findByUsername(username);
            if (existingUser != null) {
                runOnUiThread(() -> {
                    Toast.makeText(SignupActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                });
                return;
            }

            // Create a new user object
            User newUser = new User(username, password);

            // Set the user as an admin if necessary
            if (newUser.isAdmin) {
                newUser.promote();
            }

            // Insert the new user into the database
            long userId = repository.insert(newUser);

            if (userId != -1) {
                // Insertion successful
                runOnUiThread(() -> {
                    // Show a welcome message based on the user's role
                    if (newUser.isAdmin()) {
                        Toast.makeText(SignupActivity.this, "Welcome, admin", Toast.LENGTH_SHORT).show();
                        adminLandingActivity();
                    } else {
                        Toast.makeText(SignupActivity.this, "Welcome, " + username, Toast.LENGTH_SHORT).show();
                        userLandingActivity();
                    }
                });
            } else {
                // Insertion failed
                runOnUiThread(() -> {
                    Toast.makeText(SignupActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    /**
     * ==== Navigation methods ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ====
     */

    private void userLandingActivity() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void signInActivity() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void adminLandingActivity() {
        Intent intent = new Intent(SignupActivity.this, AdminMainActivity.class);
        startActivity(intent);
    }
}