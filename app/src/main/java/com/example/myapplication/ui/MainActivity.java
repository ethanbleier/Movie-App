package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.myapplication.R;
import com.example.myapplication.data.MovieRepository;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    public static final String TAG = "DAC_MOVIE-APP";

    // movie model params
    String title = "", director = "", genre = "", year = "";

    // user username for tvUsername
    private LiveData<String> signedInUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieRepository = new MovieRepository(getApplication());
        userRepository = new UserRepository(getApplication());

        Button btnBack = findViewById(R.id.back);
        Button btnAddMovie = findViewById(R.id.add_button);
        Button btnEditUser = findViewById(R.id.username);

        binding.addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "add movie info next!", Toast.LENGTH_SHORT).show();
                navigateToAddMovieActivity();
            }
        });

        signedInUsername = (LiveData<String>) userRepository.getUsername();

        signedInUsername.observe(this, signedInUsername -> {
            // idk why the button text must be a char seq
            if (signedInUsername != null) {
                binding.username.setText((CharSequence) userRepository.getUsername());
            }
        });

        binding.username.setOnClickListener(v -> navigateToEditUserActivity());

        // button on click listeners
        btnBack.setOnClickListener(v -> navigateToSignUpActivity());
        btnAddMovie.setOnClickListener(v -> navigateToAddMovieActivity());
    }

    private void navigateToSignUpActivity() {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    private void navigateToAddMovieActivity() {
        Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
        startActivity(intent);
    }

    private void navigateToEditUserActivity() {
        Intent intent = new Intent(MainActivity.this, EditUserActivity.class);
        startActivity(intent);
    }
}