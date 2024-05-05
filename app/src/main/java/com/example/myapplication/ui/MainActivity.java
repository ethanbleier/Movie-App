package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.UserDao;
import com.example.myapplication.data.UserRoomDatabase;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "";
    ActivityMainBinding binding;

    private final UserRoomDatabase db = UserRoomDatabase.getDatabase(getApplicationContext());
    UserDao userDao = db.userDao();

    int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button back = findViewById(R.id.back);
        Button addMovie = findViewById(R.id.add_button);

        binding.addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "add movie info next!", Toast.LENGTH_LONG).show();
            }
        });

//        loginUser();


//        String currentUserUsername = getLoggedInUsername();
//        if (currentUserUsername != null) {
//            username.setText(currentUserUsername);
//        } else {
//            username.setText("DB ERROR\nNo Username Found");
//        }

        // button on click listeners
        back.setOnClickListener(v -> navigateToSignUpActivity());
        addMovie.setOnClickListener(v -> navigateToAddMovieActivity());
    }

//    private String getLoggedInUsername() {
//        User loggedInUser = UserManager.getLoggedInUser();
//        if (loggedInUser != null) {
//            return loggedInUser.getUsername();
//        }
//        return "No user found";
//    }
//
//    private void loginUser() {
//        userDao.signUp();
//    }

    private void navigateToSignUpActivity() {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    private void navigateToAddMovieActivity() {
        Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
        startActivity(intent);
    }
}