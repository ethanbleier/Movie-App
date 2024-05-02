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
import com.example.myapplication.model.Movie;

public class UpdateMovieActivity extends AppCompatActivity {
    private EditText etTitle, etDirector, etGenre, etYear;
    Movie movie = null; //TODO: access movie object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        etTitle = findViewById(R.id.etTitle);
        etDirector = findViewById(R.id.etDirector);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        Button btnUpdateMovie = findViewById(R.id.btnUpdateMovie);
        Button btnBack = findViewById(R.id.btnBack);

        btnUpdateMovie.setOnClickListener(v -> updateMovie());
        btnBack.setOnClickListener(v -> navigateToEditMovie());
    }

    private void updateMovie() {
        String title = etTitle.getText().toString().trim();
        String director = etDirector.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String year = etYear.getText().toString().trim();

        //TODO: handle missing fields

        movie.setmTitle(title);
        movie.setmDirector(director);
        movie.setmGenre(genre);
        movie.setmYear(year);

        Toast.makeText(UpdateMovieActivity.this, "Movie Updated", Toast.LENGTH_SHORT).show();
    }
    private void navigateToEditMovie() {
        Intent intent = new Intent(UpdateMovieActivity.this, EditMovieActivity.class);
        startActivity(intent);
    }
}