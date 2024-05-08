package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.data.MovieRepository;
import com.example.myapplication.data.MovieRoomDatabase;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.data.model.Movie;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.data.model.User;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "MAIN_ACTIVITY_USER_ID";

    private UserRepository userRepository;
    private MovieRepository movieRepository;

    public static final String TAG = "DAC_MOVIE-APP";

    private User user;

    RecyclerView recyclerViewMovies;
    MovieAdapter movieAdapter;
    private final List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.myapplication.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        // Repository Initialization
        this.userRepository = new UserRepository(getApplication());
        this.movieRepository = new MovieRepository(getApplication());
        int loggedInUserId = intent.getIntExtra(MAIN_ACTIVITY_USER_ID, -1);

        // Movie recycler initialization
        this.recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        this.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        this.movieAdapter = new MovieAdapter(movieList);
        this.recyclerViewMovies.setAdapter(movieAdapter);

        // Driver functions
        loginUser();
        loadMovies();

        // Add movie button
        findViewById(R.id.add_button).setOnClickListener(v -> navigateToAddMovieActivity());

        // Account button
        findViewById(R.id.btnAccount).setOnClickListener(v -> navigateToEditUserActivity());
    }

    // loadMovies will update the view when the user inserts/deletes another movie
    @SuppressLint("NotifyDataSetChanged")
    private void loadMovies() {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            LiveData<List<Movie>> moviesLiveData = movieRepository.getAllMovies();
            runOnUiThread(() -> {
                moviesLiveData.observe(this, movies -> {
                    movieList.clear();
                    movieList.addAll(movies);
                    movieAdapter.notifyDataSetChanged();
                });
            });
        });
    }

    // Login user method
    private void loginUser() {
        LiveData<User> userObserver = userRepository.getLoggedInUser();
        userObserver.observe(this, user -> {
            this.user = user;
            if (this.user != null) {
                invalidateOptionsMenu();
            }
        });
    }

    // Adapts movies into the Recycler view
    private static class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
        private final List<Movie> movieList;

        public MovieAdapter(List<Movie> movieList) {
            this.movieList = movieList;
        }

        // On Create bind holder
        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_movie, parent, false);
            return new MovieViewHolder(itemView);
        }

        // Bind view bolder
        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            if (movieList != null && position >= 0 && position < movieList.size()) {
                Movie movie = movieList.get(position);
                holder.textViewTitle.setText(movie.getTitle());
                holder.textViewDirector.setText(movie.getDirector());
                holder.textViewGenre.setText(movie.getGenre());
                holder.textViewYear.setText(movie.getYear());
            }
        }

        // items getter
        @Override
        public int getItemCount() {
            return movieList != null ? movieList.size() : 0;
        }

        // ViewHolder for the movie attributes. extends the RecyclerViewViewHolder
        public static class MovieViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;
            TextView textViewDirector;
            TextView textViewGenre;
            TextView textViewYear;

            // Constructor that takes a position in the recycler and sets the movie object attributes
            public MovieViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewDirector = itemView.findViewById(R.id.textViewDirector);
                textViewGenre = itemView.findViewById(R.id.textViewGenre);
                textViewYear = itemView.findViewById(R.id.textViewYear);
            }
        }
    }

    /**
     * ==== Navigation methods ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ==== ====
     */

    private void navigateToAddMovieActivity() {
        Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
        startActivity(intent);
    }

    private void navigateToEditUserActivity() {
        Intent intent = new Intent(MainActivity.this, EditUserActivity.class);
        startActivity(intent);
    }
}