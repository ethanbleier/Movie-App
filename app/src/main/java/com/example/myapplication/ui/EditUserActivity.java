package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRoomDatabase;

public class EditUserActivity extends AppCompatActivity {
    private UserDao userDao;
    private EditText etUsername;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDeleteUser = findViewById(R.id.btnDeleteUser);
        Button btnBack = findViewById(R.id.btnBack);


        //update button listener
        btnUpdate.setOnClickListener(v -> {
            updateUser();

            Toast.makeText(EditUserActivity.this, "Account info updated", Toast.LENGTH_SHORT).show();
        });


        // delete user button listener
        btnDeleteUser.setOnClickListener(v -> {
            //TODO: delete user from database

            navigateToLoginActivity();
        });

        // back button listener
        btnBack.setOnClickListener(v -> navigateToMainActivity());
    }

    private void updateUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(EditUserActivity.this, "fill in all fields please", Toast.LENGTH_SHORT).show();
        }

        //TODO: update user info in data base

    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(EditUserActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //TODO: Add date
}