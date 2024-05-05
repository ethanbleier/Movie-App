package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private ActivityLoginBinding binding;

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserRepository repository = new UserRepository(getApplication());

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFieldFromDisplay();
            }
        });

        // Sign up button
        findViewById(R.id.btnSignUp).setOnClickListener(v -> signUpActivity());
    }

    // here we return true if the username or password inputted is valid.
    // if the user/pwd is invalid, validate returns false
    private boolean validate(String text) {
        if (text == null) {
            return false;
        }

        return !text.isEmpty();
    }

    private void getFieldFromDisplay() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (validate(username) && validate(password)) {
            this.username = username;
            this.password = password;

            login();
        }

        if (!validate(username)) {
            binding.etUsername.setError("Invalid username!");
        }

        if (!validate(password)) {
            binding.etPassword.setError("Invalid password!");
        }
    }

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