package com.example.movie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WorldViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private final LiveData<List<Movie>> mAllMovies;

    public WorldViewModel(Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
    }

    LiveData<List<Movie>> getmAllMovies() {
        return mAllMovies;
    }

    public void insert(Movie movie) {
        mRepository.insert(movie);
    }
}
