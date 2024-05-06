package com.example.myapplication.data;
import android.app.Application;
import android.util.Log;

import com.example.myapplication.data.model.Movie;
import com.example.myapplication.ui.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MovieRepository {
    private final MovieDao movieDao;
    private final ArrayList<Movie> allMovies;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        this.movieDao = db.movieDao();
        this.allMovies = (ArrayList<Movie>) this.movieDao.getAll();
    }

    public void deleteAllMovies() {
        MovieRoomDatabase.databaseWriteExecutor.execute(this.movieDao::deleteAllMovies);
    }

    public ArrayList<Movie> getAllMovies() {
        Future<ArrayList<Movie>> future = MovieRoomDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Movie>>() {
                    @Override
                    public ArrayList<Movie> call() throws Exception {
                        return (ArrayList<Movie>) movieDao.getAll();
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Problem getting all movies in the movie repository");
        }
        return null;
    }

    void insert(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }

    void delete(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() ->{
            movieDao.delete(movie);
        });
    }
}
