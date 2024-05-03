package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.Review;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("DELETE FROM review")
    void deleteAllReviews();

    @Query("SELECT * FROM reviews")
    LiveData<List<Review>> getAll();

    @Query("SELECT * FROM reviews WHERE movieId LIKE :movieId")
    Review findByMovieId(int movieId);

    @Insert()
    void insert(Review review);

    @Query("SELECT EXISTS (SELECT * FROM user WHERE movieId=:movieId)")
    boolean is_reviewed(int movieId);
}



