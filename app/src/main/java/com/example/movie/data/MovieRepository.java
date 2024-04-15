package com.example.movie.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.movie.data.MovieDao;
import com.example.movie.data.MovieRoomDatabase;
import com.example.movie.model.Movie;

import java.util.List;

class MovieRepository {
    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getMovieList();
    }

    LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    void insert(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.insert(movie);
        });
    }
}