package com.example.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.myapplication.R;
import com.example.myapplication.data.MovieRepository;
import com.example.myapplication.data.UserRepository;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.data.model.User;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "MAIN_ACTIVITY_USER_ID";

    static final String SHARED_PREFERENCE_USERID_KEY = "SHARED_PREFERENCE_USER_ID_KEY";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "SAVED_INSTANCE_STATE_USERID_KEY";

    private static final int LOGGED_OUT = -1;
    private ActivityMainBinding binding;

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    public static final String TAG = "DAC_MOVIE-APP";

    private User user;
    private int loggedInUserId;

    Button btnUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieRepository = new MovieRepository(getApplication());
        userRepository = new UserRepository(getApplication());

        loginUser();

        Button btnAddMovie = findViewById(R.id.add_button);

        binding.addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "add movie info next!", Toast.LENGTH_SHORT).show();
                navigateToAddMovieActivity();
            }
        });

//        menuOptions.setOnClickListener(v - > m)
        btnAddMovie.setOnClickListener(v -> navigateToAddMovieActivity());
    }

    private void loginUser() {
        LiveData<User> userObserver = userRepository.getLoggedInUser();
        userObserver.observe(this, user -> {
            this.user = user;
            if (this.user != null) {
                invalidateOptionsMenu();
            } else {

            }

        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.usernameMenuItem);
        item.setVisible(true);
        if (user != null) {
            item.setTitle(user.getUsername() != null ? user.getUsername() : "");
            item.setVisible(true);
        } else {
            item.setVisible(false);
        }
        return true;
    }

    private void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();
        sharedPreferenceEditor.putInt(getString(R.string.preference_user_id_key), loggedInUserId);
        sharedPreferenceEditor.apply();
    }

    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(String.valueOf(MAIN_ACTIVITY_USER_ID), userId);
        return intent;
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