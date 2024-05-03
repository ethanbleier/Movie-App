package com.example.myapplication.data;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.myapplication.model.Movie;

import java.util.List;

public class MovieRepository {
    private static MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAll();
    }

    public void deleteAllMovies() {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.deleteAllMovies();
        });
    }

    LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    void insert(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.insert(movie);
        });
    }

    void delete(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() ->{
            mMovieDao.delete(movie);
        });
    }
}
