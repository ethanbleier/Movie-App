package com.example.movie;

import com.example.myapplication.data.model.Movie;

import org.junit.Assert;
import org.junit.Test;

public class MovieTest {
    @Test
    public void testMovieClass() {
        String title = "Dune 3";
        String director = "Denis Villeneuve";
        String genre = "Sci-fi";
        String year = "2027";

        Movie movie = new Movie(title, director, genre, year);

        Assert.assertEquals(title, movie.getTitle());
        Assert.assertEquals(director, movie.getDirector());
        Assert.assertEquals(genre, movie.getGenre());
        Assert.assertEquals(year, movie.getYear());
    }

    @Test
    public void testMovieSetters() {
        Movie movie = new Movie("", "", "", "");

        String title = "Star Wars: A New Hope";
        String director = "George Lucas";
        String genre = "Sci-fi";
        String year = "1977";

        movie.setTitle(title);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setYear(year);

        Assert.assertEquals(title, movie.getTitle());
        Assert.assertEquals(director, movie.getDirector());
        Assert.assertEquals(genre, movie.getGenre());
        Assert.assertEquals(year, movie.getYear());
    }
}
