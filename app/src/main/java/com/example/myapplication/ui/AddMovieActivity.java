package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.MovieRepository;
import com.example.myapplication.data.MovieRoomDatabase;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.data.model.Movie;
import com.example.myapplication.data.model.User;


public class AddMovieActivity extends AppCompatActivity {
    private EditText etTitle, etDirector, etGenre, etYear;
    private MovieRepository repository;

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

        repository = new MovieRepository(getApplication());

        btnBack.setOnClickListener(v -> navigateToMainActivity());
        btnAddMovie.setOnClickListener(v -> addMovie());

    }

    private void addMovie() {
        String title = etTitle.getText().toString().trim();
        String director = etDirector.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String year = etYear.getText().toString().trim();

        // input validation
        if (title.isEmpty() || director.isEmpty() || genre.isEmpty() || year.isEmpty()) {
            Toast.makeText(AddMovieActivity.this, "Entry must include all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            // checking if movie already exists with a dummy object
            Movie existingMovie = repository.findByTitle(title);
            if (existingMovie != null) {
                runOnUiThread(() -> {
                    Toast.makeText(AddMovieActivity.this, "Movie already exists!", Toast.LENGTH_SHORT).show();
                });
                return;
            }

            // once we verify there is a space for this movie we create the object
            Movie newMovie = new Movie(title, director, genre, year);

            long movieId = repository.insert(newMovie);

            runOnUiThread(() -> {
                if (movieId != -1) {
                    // Insertion passed
                    Toast.makeText(AddMovieActivity.this, newMovie.getTitle() + " added to Your List.", Toast.LENGTH_SHORT).show();
                    navigateToMainActivity();
                } else {
                    // Insertion failed
                    Toast.makeText(AddMovieActivity.this, "Error, problem with movie insertion", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(AddMovieActivity.this, MainActivity.class);
        startActivity(intent);
    }
}