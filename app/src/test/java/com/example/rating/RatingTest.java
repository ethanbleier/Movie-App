package com.example.rating;

import com.example.myapplication.data.model.Rating;

import org.junit.Assert;
import org.junit.Test;

public class RatingTest {
    @Test
    public void testSetup() {
        float ratingValue = 4.5f;
        Rating rating = new Rating(ratingValue);

        Assert.assertEquals(ratingValue, rating.getRating(), 0.0f);
    }

    @Test
    public void testSetters() {
        Rating rating = new Rating(0.0f);

        int userId = 1;
        int movieId = 10;
        float ratingValue = 3.7f;

        rating.setUserId(userId);
        rating.setMovieId(movieId);
        rating.setRating(ratingValue);

        Assert.assertEquals(userId, rating.getUserId());
        Assert.assertEquals(movieId, rating.getMovieId());
        Assert.assertEquals(ratingValue, rating.getRating(), 0.0f);
    }
}