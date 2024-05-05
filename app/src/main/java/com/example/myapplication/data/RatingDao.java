package com.example.myapplication.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.Rating;

import java.util.List;

@Dao
public interface RatingDao {

    @Query("Delete FROM ratings")
    void deleteAllRatings();

    @Query("SELECT * FROM ratings")
    LiveData<List<Rating>> getAllRatings();

    @Query("SELECT * FROM ratings WHERE movieId = :movieId")
    LiveData<Rating> findRatingsByMovie(int movieId);

    @Insert()
    void insertRating(Rating rating);

}
