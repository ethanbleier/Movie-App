package com.example.myapplication.data;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.model.Movie;

import java.util.List;
import java.util.concurrent.Executor;

public class MovieRepository {
    private static MovieDao movieDao = null;
    private static LiveData<List<Movie>> movies;
    public Executor databaseWriteExecutor;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();
    }

    public void deleteAllMovies() {
        MovieRoomDatabase.databaseWriteExecutor.execute(movieDao::deleteAllMovies);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public Movie findByTitle(String title) {
        return movieDao.findByTitle(title);
    }

    public long insert(Movie movie) {
        return movieDao.insert(movie);
    }

    void delete(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() ->{
            movieDao.delete(movie);
        });
    }
}
