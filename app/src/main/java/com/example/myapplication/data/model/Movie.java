package com.example.myapplication.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
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

    public int getmId() {
        return id;
    }

    public void setmId(int mId) {
        this.id = mId;
    }

    public String getmTitle() {
        return title;
    }

    public String getmDirector() {
        return director;
    }

    public String getmGenre() {
        return genre;
    }

    public String getmYear() {
        return year;
    }

    public void setmTitle(String mTitle) {
        this.title = mTitle;
    }

    public void setmDirector(String mDirector) {
        this.director = mDirector;
    }

    public void setmGenre(String mGenre) {
        this.genre = mGenre;
    }

    public void setmYear(String mYear) {
        this.year = mYear;
    }
}
