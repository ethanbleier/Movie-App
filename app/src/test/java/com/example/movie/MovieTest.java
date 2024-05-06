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

        Assert.assertEquals(title, movie.getmTitle());
        Assert.assertEquals(director, movie.getmDirector());
        Assert.assertEquals(genre, movie.getmGenre());
        Assert.assertEquals(year, movie.getmYear());
    }

    @Test
    public void testMovieSetters() {
        Movie movie = new Movie("", "", "", "");

        String title = "Star Wars: A New Hope";
        String director = "George Lucas";
        String genre = "Sci-fi";
        String year = "1977";

        movie.setmTitle(title);
        movie.setmDirector(director);
        movie.setmGenre(genre);
        movie.setmYear(year);

        Assert.assertEquals(title, movie.getmTitle());
        Assert.assertEquals(director, movie.getmDirector());
        Assert.assertEquals(genre, movie.getmGenre());
        Assert.assertEquals(year, movie.getmYear());
    }
}
