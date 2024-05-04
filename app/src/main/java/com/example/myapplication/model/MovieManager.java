package com.example.myapplication.model;

import android.app.Application;
import com.example.myapplication.data.MovieRepository;

public class MovieManager {
    private final MovieRepository movieRepository;
    private static Movie currMovie;

    public MovieManager(Application application) {
        movieRepository = new MovieRepository(application);
    }

    public static Movie getCurrMovie() {
        return currMovie;
    }

}
