package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Movie;


public class AddMovieActivity extends AppCompatActivity {
    private EditText etTitle, etDirector, etGenre, etYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        etTitle = findViewById(R.id.etTitle);
        etDirector = findViewById(R.id.etDirector);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        Button btnAddMovie = findViewById(R.id.btnAddMovie);
        Button btnBack = findViewById(R.id.btnBack);


        btnBack.setOnClickListener(v -> navigateToMainActivity());
        btnAddMovie.setOnClickListener(v -> addMovie());

    }
    private void addMovie() {
        String title = etTitle.getText().toString().trim();
        String director = etDirector.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String year = etYear.getText().toString().trim();

        //input validation
        if(title.isEmpty()) {
            Toast.makeText(AddMovieActivity.this, "Entry must include a title", Toast.LENGTH_SHORT).show();
        }

        //TODO: add movie obj to database?
        Movie movie = new Movie(title, director, genre, year);
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(AddMovieActivity.this, MainActivity.class);
        startActivity(intent);
    }
}