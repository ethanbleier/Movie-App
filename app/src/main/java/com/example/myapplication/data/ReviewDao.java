package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.User;

@Dao
public interface ReviewDao {

    @Query("DELETE FROM review")
    void deleteAllReviews();

    @Query("SELECT * FROM reviews")
    LiveData<List<reviews>> getAll();

    @Query("SELECT * FROM reviews WHERE movieId LIKE :movieId")
    User findByUsername(String username);
}



