package com.example.myapplication.data;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.myapplication.model.Movie;

import java.util.List;

public class MovieRepository {
    private static MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    public MovieRepository(Application application) {
        RatingRoomDatabase db = RatingRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAll();
    }

    public void deleteAllMovies() {
        RatingRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.deleteAllMovies();
        });
    }

    LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    void insert(Movie movie) {
        RatingRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.insert(movie);
        });
    }

    void delete(Movie movie) {
        RatingRoomDatabase.databaseWriteExecutor.execute(() ->{
            mMovieDao.delete(movie);
        });
    }
}
