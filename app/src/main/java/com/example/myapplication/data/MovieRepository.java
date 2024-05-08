package com.example.myapplication.data;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.model.Movie;

import java.util.List;
import java.util.concurrent.Executor;

public class MovieRepository {
    private final MovieDao movieDao;
    public Executor databaseWriteExecutor;
    LiveData<List<Movie>> movies;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        this.movieDao = db.movieDao();
        movies = movieDao.getAll();
    }

    public void deleteAllMovies() {
        MovieRoomDatabase.databaseWriteExecutor.execute(this.movieDao::deleteAllMovies);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
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
