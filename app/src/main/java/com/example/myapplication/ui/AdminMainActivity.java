package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        Button btnSignOut = findViewById(R.id.btnSignOut);
        Button btnAddNewUser = findViewById(R.id.btnAddNewUser);

        btnAddNewUser.setOnClickListener(v -> navigateToAddUserActivity());

        btnSignOut.setOnClickListener(v -> {
            // I don't think sign out is necessary, but we'll see. Hopefully it is not.
            navigateToLoginActivity();
        });

    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(AdminMainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void navigateToAddUserActivity() {
        Intent intent = new Intent(AdminMainActivity.this, AddUserActivity.class);
        startActivity(intent);
    }
}