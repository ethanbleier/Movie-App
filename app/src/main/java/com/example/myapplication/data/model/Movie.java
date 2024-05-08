package com.example.myapplication.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "director")
    public String director;

    @ColumnInfo(name = "genre")
    public String genre;

    @ColumnInfo(name = "year")
    public String year;

    public Movie(String title, String director, String genre, String year) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        this.id = mId;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public void setDirector(String mDirector) {
        this.director = mDirector;
    }

    public void setGenre(String mGenre) {
        this.genre = mGenre;
    }

    public void setYear(String mYear) {
        this.year = mYear;
    }
}
