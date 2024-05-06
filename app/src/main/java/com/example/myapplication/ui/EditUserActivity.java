package com.example.myapplication.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivityEditUserBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.time.LocalDateTime;

public class EditUserActivity extends AppCompatActivity {
    private UserDao userDao;
    private UserRepository userRepository;
    private EditText etUsername, etPassword;
    private LocalDateTime date;
    private ActivityEditUserBinding binding;
    private int loggedInUserId;

    private static final int LOGGED_OUT = -1;
    private static final int EDIT_USER_ACTIVITY_USER_ID = -1;

    private static final String MAIN_ACTIVITY_USER_ID = "MAIN_ACTIVITY_USER_ID";

    static final String SHARED_PREFERENCE_USERID_KEY = "SHARED_PREFERENCE_USER_ID_KEY";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "SAVED_INSTANCE_STATE_USERID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.userRepository = new UserRepository(getApplication());


        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDeleteUser = findViewById(R.id.btnDeleteUser);
        Button btnBack = findViewById(R.id.btnBack);


        // UPDATE button
        btnUpdate.setOnClickListener(v -> {
            updateUser();

            Toast.makeText(EditUserActivity.this, "Account info updated", Toast.LENGTH_SHORT).show();
            navigateToMainActivity();
        });

        // BACK button
        btnBack.setOnClickListener(v -> navigateToMainActivity());

        // DELETE user button listener
        btnDeleteUser.setOnClickListener(v -> {
            userDao.deleteUser();

            navigateToLoginActivity();
        });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditUserActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Sign out?");

        alertBuilder.setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logoutUser();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    private void logoutUser() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreferences();
        getIntent().putExtra(String.valueOf(EDIT_USER_ACTIVITY_USER_ID), LOGGED_OUT);

        startActivity(LoginActivity.loginActivityIntentFactory(getApplicationContext(), loggedInUserId));
    }

    private void deleteUser() {
        loggedInUserId = -1;
        updateSharedPreferences();
        getIntent().putExtra(String.valueOf(EDIT_USER_ACTIVITY_USER_ID), LOGGED_OUT);

        startActivity(LoginActivity.loginActivityIntentFactory(getApplicationContext(), -1));
    }

    private void updateUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(EditUserActivity.this, "fill in all fields please", Toast.LENGTH_SHORT).show();
        }

        //TODO: update user info in data base

    }

    private void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();
        sharedPreferenceEditor.putInt(getString(R.string.preference_user_id_key), loggedInUserId);
        sharedPreferenceEditor.apply();
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