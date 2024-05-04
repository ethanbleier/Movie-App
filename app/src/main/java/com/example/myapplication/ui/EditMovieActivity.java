package com.example.myapplication.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.Movie;
import com.example.myapplication.model.MovieManager;
import com.example.myapplication.model.User;
import com.example.myapplication.model.UserManager;

import org.w3c.dom.Text;

public class EditMovieActivity extends AppCompatActivity {
    Movie currMovie = MovieManager.getCurrMovie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        TextView tvMovieTitle = findViewById(R.id.tvMovieTitle);
        TextView tvMovieDirector = findViewById(R.id.tvMovieDirector);
        TextView tvMovieGenre = findViewById(R.id.tvMovieGenre);
        TextView tvMovieYear = findViewById(R.id.tvMovieYear);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnEditMovie = findViewById(R.id.btnEditMovie);
        Button btnDeleteMovie = findViewById(R.id.btnDeleteMovie);

        //Update text views with movie info
        tvMovieTitle.setText(getMovieTitle());
        tvMovieDirector.setText(getMovieDirector());
        tvMovieGenre.setText(getMovieGenre());
        tvMovieYear.setText(getMovieYear());

        // back button listener
        btnBack.setOnClickListener(v -> {
            // navigation based on user's admin status
            User currUser = UserManager.getLoggedInUser();
            if(currUser.isAdmin()) {
                navigateToAdminMainActivity();
            } else {
                navigateToMainActivity();
            }

        });

        // update movie button listener
        btnEditMovie.setOnClickListener(v -> navigateToUpdateMovieActivity());

        // delete movie button listener
        btnDeleteMovie.setOnClickListener(v -> {
            // alert set up
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditMovieActivity.this);
            final AlertDialog alertDialog = alertBuilder.create();
            alertBuilder.setMessage("Are you sure you want to delete this movie?\n");
            // user selects "yes"
            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deleteMovie();
                }
            });
            // user selects "No"
            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }
            });
        });

    }

    // probably overcomplicated, but gets info to set textview text
    private String getMovieTitle(){
        String title = currMovie.getmTitle();
        if(title != null) {
            return title;
        }
        return "No Title found";
    }
    private String getMovieDirector() {
        String director = currMovie.getmDirector();
        if(director != null) {
            return director;
        }
        return "No director found";
    }
    private String getMovieGenre() {
        String genre = currMovie.getmGenre();
        if(genre != null){
            return genre;
        }
        return "No genre found";
    }
    private String getMovieYear() {
        String year = currMovie.getmYear();
        if(year != null) {
            return year;
        }
        return "No year found";
    }


    private void deleteMovie() {
        //TODO: Delete movie from database, include toast confirming deletion
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