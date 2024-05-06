package com.example.review;

import com.example.myapplication.data.model.Review;

import org.junit.Assert;
import org.junit.Test;

public class ReviewTest {
    @Test
    public void testReviewCreation() {
        int userId = 2;
        int movieId = 20;
        String content = "Great movie!";

        Review review = new Review(userId, movieId, content);

        Assert.assertEquals(userId, review.getUserId());
        Assert.assertEquals(movieId, review.getMovieId());
        Assert.assertEquals(content, review.getContent());
    }

    @Test
    public void testReviewSetters() {
        Review review = new Review(0, 0, "");

        int userId = 3;
        int movieId = 30;
        String content = "Highly recommended!";

        review.setUserId(userId);
        review.setMovieId(movieId);
        review.setContent(content);

        Assert.assertEquals(userId, review.getUserId());
        Assert.assertEquals(movieId, review.getMovieId());
        Assert.assertEquals(content, review.getContent());
    }
}