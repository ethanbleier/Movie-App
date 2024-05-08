package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.data.model.User;
import com.example.myapplication.databinding.ActivityEditUserBinding;

import java.time.LocalDateTime;

public class EditUserActivity extends AppCompatActivity {
    private UserDao userDao;
    private EditText etUsername, etPassword;
    private LocalDateTime date;
    private int loggedInUserId;
    private UserRepository repository;

    private static final int LOGGED_OUT = -1;
    private static final int EDIT_USER_ACTIVITY_USER_ID = -1;

    private static final String MAIN_ACTIVITY_USER_ID = "MAIN_ACTIVITY_USER_ID";

    static final String SHARED_PREFERENCE_USERID_KEY = "SHARED_PREFERENCE_USER_ID_KEY";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "SAVED_INSTANCE_STATE_USERID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.myapplication.databinding.ActivityEditUserBinding binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.repository = new UserRepository(getApplication());

        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDeleteUser = findViewById(R.id.btnDeleteUser);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnSignOut = findViewById(R.id.btnSignOut);

        // UPDATE button
        btnUpdate.setOnClickListener(v -> updateUser());

        // SIGN OUT user button listener
        btnSignOut.setOnClickListener(v -> showLogoutDialog());

        // Activity BACK button listener
        btnBack.setOnClickListener(v -> navigateToMainActivity());

        // DELETE user button listener
        btnDeleteUser.setOnClickListener(v -> showDeleteDialog());
    }

    // Delete dialog alert
    private void showDeleteDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditUserActivity.this);
        alertBuilder.setMessage("Erase your account?");

        alertBuilder.setPositiveButton("Erase account", (dialog, which) -> deleteUser());

        alertBuilder.setNegativeButton("Keep account", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    // Logout dialog Alert
    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditUserActivity.this);

        alertBuilder.setMessage("Sign out?");

        alertBuilder.setPositiveButton("Sign out", (dialog, which) -> logoutUser());

        alertBuilder.setNegativeButton("Stay signed in", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    // Sign out function
    // goes to login activity
    private void logoutUser() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreferences();
        getIntent().putExtra(String.valueOf(EDIT_USER_ACTIVITY_USER_ID), LOGGED_OUT);

        Intent intent = new Intent(EditUserActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // Delete user function
    // goes to login activity
    private void deleteUser() {
        loggedInUserId = -1;
        updateSharedPreferences();
        getIntent().putExtra(String.valueOf(EDIT_USER_ACTIVITY_USER_ID), LOGGED_OUT);

        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            User currentUser = repository.getLoggedInUser().getValue();
            if (currentUser != null) {
                repository.deleteUser(currentUser.getId());
                runOnUiThread(() -> {
                    Intent intent = new Intent(EditUserActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                });
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(EditUserActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    // This method performs database logic to update the username in the account settings.
    private void updateUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // check for blank fields
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(EditUserActivity.this, "fill in all fields please", Toast.LENGTH_SHORT).show();
        }

        // Call the database to update user info
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            User currentUser = repository.getLoggedInUser().getValue();
            if (currentUser != null) {
                // Update the user properties
                currentUser.setUsername(username);
                currentUser.setPassword(password);

                // Save the updated user using the UserRepository
                repository.update(currentUser);

                runOnUiThread(() -> {
                    Toast.makeText(EditUserActivity.this, "User information updated", Toast.LENGTH_SHORT).show();
                    navigateToMainActivity();
                });
            } else {
                runOnUiThread(() -> Toast.makeText(EditUserActivity.this, "User requested not found", Toast.LENGTH_SHORT).show());
            }
        });

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