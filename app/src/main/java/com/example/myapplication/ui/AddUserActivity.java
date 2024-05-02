package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Checkable;
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
import com.example.myapplication.model.User;

public class AddUserActivity extends AppCompatActivity {
    private UserDao userDao;
    private EditText etUsername, etPassword;
    private Checkable etIsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
        userDao = db.userDao();

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnAddNewUser = findViewById(R.id.btnAddNewUser);
        Button btnBack = findViewById(R.id.btnBack);
        etIsAdmin = findViewById(R.id.etIsAdmin);

        btnBack.setOnClickListener(v -> navigateToAdminMainActivity());
        btnAddNewUser.setOnClickListener(v -> signupUser());
    }

    private void signupUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        boolean isAdmin = Boolean.parseBoolean(etIsAdmin.toString().trim());

        // input validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(AddUserActivity.this, "fill in all fields please", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new user and add to db
        User user = new User(username, password, isAdmin);
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);

        });
    }

    private void navigateToAdminMainActivity() {
        Intent intent = new Intent(AddUserActivity.this, AdminMainActivity.class);
        startActivity(intent);
    }
}