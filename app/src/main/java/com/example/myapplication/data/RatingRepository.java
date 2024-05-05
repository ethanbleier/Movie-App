package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.myapplication.model.Rating;
import com.example.myapplication.model.Review;

import java.util.List;
public class RatingRepository {
    private static RatingDao mRatingDao;
    private final LiveData<List<Rating>> mAllRatings;

    public RatingRepository(Application application) {
        RatingRoomDatabase db = RatingRoomDatabase.getDatabase(application);
        mRatingDao = db.ratingDao();
        mAllRatings = mRatingDao.getAllRatings();
    }

    public void deleteAllRatings() {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRatingDao.deleteAllRatings();
        });
    }

    LiveData<List<Rating>> getAllReviews() {
        return mAllRatings;
    }

    void insertRating(Rating rating) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRatingDao.insertRating(rating);
        });
    }

}
