package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.data.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAllMovies();

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movie WHERE title LIKE :title")
    Movie findByTitle(String title);

    @Query("SELECT * FROM movie WHERE director LIKE :director")
    Movie findByDirector(String director);

    @Query("SELECT * FROM movie WHERE genre LIKE :genre")
    Movie findByGenre(String genre);

    @Query("SELECT * FROM movie WHERE year LIKE :year")
    Movie findByYear(String year);

    @Delete
    void delete(Movie movie);

}
