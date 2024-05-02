package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class AdminEditUserActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> navigateToAdminMainActivity());
    }

    private void navigateToAdminMainActivity() {
        Intent intent = new Intent(AdminEditUserActivity.this, AdminMainActivity.class);
        startActivity(intent);
    }

    private void navigateToEditMovieActivity() {
        Intent intent = new Intent(AdminEditUserActivity.this, EditMovieActivity.class);
        startActivity(intent);
    }
}