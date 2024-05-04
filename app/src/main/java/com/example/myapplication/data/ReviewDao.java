package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.Review;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("DELETE FROM reviews")
    void deleteAllReviews();

    @Query("SELECT * FROM reviews")
    LiveData<List<Review>> getAllReviews();

    @Query("SELECT * FROM reviews WHERE movieId LIKE :movieId")
    Review findReviewByMovieId(int movieId);

    @Insert()
    void insertReview(Review review);

    @Query("SELECT EXISTS (SELECT * FROM reviews WHERE movieId=:movieId)")
    boolean is_reviewed(int movieId);
}



