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
import com.example.myapplication.model.User;

public class EditMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        //TODO: access current logged in user info
        User currUser = null;

        Button btnBack = findViewById(R.id.btnBack);
        Button btnEditMovie = findViewById(R.id.btnEditMovie);
        Button btnDeleteMovie = findViewById(R.id.btnDeleteMovie);

        // back button listener
        btnBack.setOnClickListener(v -> {
            // navigation based on user's admin status
            if(currUser.isAdmin()) {
                navigateToAdminMainActivity();
            } else {
                navigateToMainActivity();
            }

        });

        btnEditMovie.setOnClickListener(v -> navigateToUpdateMovieActivity());

        btnDeleteMovie.setOnClickListener(v -> {
            //TODO: pop up notif confirming deletion
            deleteMovie();

        });

    }

    private void deleteMovie() {
        //TODO: Delete movie from database
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(EditMovieActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToAdminMainActivity() {
        Intent intent = new Intent(EditMovieActivity.this, AdminMainActivity.class);
        startActivity(intent);
    }

    private void navigateToUpdateMovieActivity() {
        Intent intent = new Intent(EditMovieActivity.this, UpdateMovieActivity.class);
        startActivity(intent);
    }
}