package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.myapplication.model.Review;

import java.util.List;

public class ReviewRepository {
    private static ReviewDao mReviewDao;
    private final LiveData<List<Review>> mAllReviews;

    public ReviewRepository(Application application) {
        ReviewRoomDatabase db = ReviewRoomDatabase.getDatabase(application);
        mReviewDao = db.reviewDao();
        mAllReviews = mReviewDao.getAllReviews();
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

    void insertReview(Review review) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mReviewDao.insertReview(review);
        });
    }
}