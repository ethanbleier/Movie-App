package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.myapplication.model.Review;
import com.example.myapplication.model.User;

import java.util.List;

public class ReviewRepository {
    private static ReviewDao mReviewDao;
    private final LiveData<List<Review>> mAllReviews;

    public ReviewRepository(Application application) {
        ReviewRoomDatabase db = ReviewRoomDatabase.getDatabase(application);
        mReviewDao = db.reviewDao();
        mAllReviews = mReviewDao.getAll();
    }

    // ... deletes all the users from db
    public void deleteAllReviews() {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mReviewDao.deleteAllReviews();
        });
    }

    LiveData<List<Review>> getAllReviews() {
        return mAllReviews;
    }

    void insert(Review review) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mReviewDao.insert(review);
        });
    }
}