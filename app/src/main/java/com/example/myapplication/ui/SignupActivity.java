package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivitySignupBinding;
import com.example.myapplication.data.model.User;

public class SignupActivity extends AppCompatActivity {
    private String username = "";
    private @NonNull ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUp.setOnClickListener(v -> getFieldFromDisplay());
        // Back to sign up page navigation
        binding.btnBackToSignIn.setOnClickListener(v -> signInActivity());
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
        String confirmedPassword = binding.etcPassword.getText().toString();

        if (validInput(username) && validInput(password) & validInput(confirmedPassword)) {
            this.username = username;

            signupUser(this.username, password, confirmedPassword);
        }

        if (!validInput(username)) {
            Toast.makeText(SignupActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
        }

        if (!validInput(password)) {
            Toast.makeText(SignupActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
//            binding.etPassword.setError("Invalid password!");
        }

        if (!validInput(confirmedPassword)) {
            Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    private void signupUser(String username, String password, String confirmedPassword) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        UserDao userDao = db.userDao();

        // we need to check if the username already exists in the db.
        User user = userDao.findByUsername(username);

        // haha good luck reading this
        // first we check that the sign up was successful, ie the insert returned a non-null user class
        if (user == null) {
            user = new User(username, password);
            if (userDao.signUp(user) != -1) {
                // need to figure out admin logic.
                Toast.makeText(SignupActivity.this, "Welcome " + username + ". ", Toast.LENGTH_SHORT).show();
                userLandingActivity();
            } else {
                Toast.makeText(SignupActivity.this, "Error signing you in, we think the username already exists in our database.", Toast.LENGTH_LONG).show();
            }
        }

        if (user.isAdmin()) {
            Toast.makeText(SignupActivity.this, "Welcome, admin.", Toast.LENGTH_SHORT).show();
            userDao.signUp(user);
            adminLandingActivity();
        } else {
            Toast.makeText(SignupActivity.this, "Welcome, " + this.username + ". ", Toast.LENGTH_SHORT).show();
            userDao.signUp(user);
            userLandingActivity();
        }
    }

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