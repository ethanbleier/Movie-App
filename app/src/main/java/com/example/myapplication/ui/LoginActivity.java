package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast; // oml
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.myapplication.data.UserRepository;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.data.model.User;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private static final String LOGIN_ACTIVITY_USER_ID = "LOGIN_ACTIVITY_USER_ID";

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("SG", "INSIDE ONCREATE()");

        getFieldFromDisplay();

        // Go to Sign Up Button Listener
        binding.btnSignUp.setOnClickListener(v -> signUpActivity());

        // Next/sign in Button Listener
        binding.btnNext.setOnClickListener(v -> getFieldFromDisplay());
    }

    static Intent loginActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(String.valueOf(LOGIN_ACTIVITY_USER_ID), userId);
        return intent;
    }

    // here we return true if the username or password inputted is valid.
    // if the user/pwd is invalid, validate returns false
    private boolean validInput(String text) {
        Log.d("SG", "INSIDE VALIDINPUT()");
        if (text == null) {
            return false;
        }

        // after confirming text is not null,
        // we return true if the text is not blank.
        return !text.isEmpty();
    }

//    private void verifyUser(String username, String password) {
//        if(username.isEmpty()) {
//            return;
//        }
//
//        LiveData<User> userObserver =
//    }

    // Method to read data from the text fields
    private void getFieldFromDisplay() {
        Log.d("SG", "INSIDE GETFIELDFROMDISPLAY()");
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

//        verifyUser(username, password);

        if (username.isEmpty() || password.isEmpty()) {
            return;
        }

        if (validInput(username) && validInput(password)) {
            this.username = username;
            this.password = password;
            Log.d("SG", username + " " + password);
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
        Log.d("SG", "INSIDE LOGIN()" + username + " " + password);
        if(UserRepository.findByUsernameAndPassword(this.username, this.password)==null) {
            Log.d("SG", "   INSIDE IF");
            return;
        }
        User user = UserRepository.findByUsernameAndPassword(this.username, this.password);
        Log.d("SG", "   1");

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